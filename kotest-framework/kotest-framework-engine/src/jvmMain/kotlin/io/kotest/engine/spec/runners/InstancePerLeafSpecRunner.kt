package io.kotest.engine.spec.runners

import io.kotest.common.ExperimentalKotest
import io.kotest.common.flatMap
import io.kotest.core.concurrency.CoroutineDispatcherFactory
import io.kotest.core.config.ProjectConfiguration
import io.kotest.core.descriptors.Descriptor
import io.kotest.core.descriptors.root
import io.kotest.core.spec.Spec
import io.kotest.core.test.NestedTest
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.core.test.TestScope
import io.kotest.engine.listener.TestEngineListener
import io.kotest.engine.spec.Materializer
import io.kotest.engine.spec.SpecExtensions
import io.kotest.engine.spec.SpecRunner
import io.kotest.engine.test.TestCaseExecutionListener
import io.kotest.engine.test.TestCaseExecutor
import io.kotest.engine.test.scheduler.TestScheduler
import io.kotest.engine.test.scopes.DuplicateNameHandlingTestScope
import io.kotest.mpp.Logger
import io.kotest.mpp.bestName
import kotlinx.coroutines.coroutineScope
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext

@ExperimentalKotest
internal class InstancePerLeafSpecRunner(
   listener: TestEngineListener,
   scheduler: TestScheduler,
   private val defaultCoroutineDispatcherFactory: CoroutineDispatcherFactory,
   private val configuration: ProjectConfiguration,
) : SpecRunner(listener, scheduler, configuration) {

   private val logger = Logger(InstancePerLeafSpecRunner::class)

   private val extensions = SpecExtensions(configuration.registry)
   private val results = mutableMapOf<TestCase, TestResult>()

   /** keeps track of tests we've already discovered */
   private val seen = mutableSetOf<Descriptor>()

   /** keeps track of tests we've already notified the listener about */
   private val ignored = mutableSetOf<Descriptor>()
   private val started = mutableSetOf<Descriptor>()

   /** we keep a count to break ties (first discovered) */
   data class Enqueued(val testCase: TestCase, val count: Int)

   private val counter = AtomicInteger(0)

   /** the queue contains tests discovered to run next. We always run the tests with the "furthest" path first. */
   private val queue = PriorityQueue(Comparator<Enqueued> { o1, o2 ->
      val o1s = o1.testCase.descriptor.depth()
      val o2s = o2.testCase.descriptor.depth()
      if (o1s == o2s) o1.count.compareTo(o2.count) else o2s.compareTo(o1s)
   })

   /** enqueues a test case that will execute in its own spec instance */
   private fun enqueue(testCase: TestCase) {
      queue.add(Enqueued(testCase, counter.incrementAndGet()))
   }

   /**
    * The intention of this runner is that each leaf [TestCase] executes in its own instance
    * of the containing [Spec] class.
    */
   override suspend fun execute(spec: Spec): Result<Map<TestCase, TestResult>> =
      runCatching {
         // we start by queuing up each root test to run in its own spec instance
         // when we find a leaf test for that instance, the spec is coloured and cannot be
         // used for further leaf tests.
         materializer.materialize(spec).forEach { root -> enqueue(root) }
         // with the queue seeded with the roots, we can keep picking a test from the queue
         // until it is empty. When it is empty that means all tests have finished and nothing
         // new is left to be found to be added to the queue
         while (queue.isNotEmpty()) {
            val (testCase, _) = queue.remove()
            executeInCleanSpec(testCase).getOrThrow()
         }
         results
      }

   private suspend fun executeInCleanSpec(test: TestCase): Result<Spec> {
      return createInstance(test.spec::class).flatMap { spec ->
         extensions.intercept(spec) {
            locateAndRunRoot(spec, test)
         } ?: Result.success(spec)
      }
   }

   // when we start a test from the queue, we must find the root test that is the ancestor of our
   // target test and begin executing that.
   private suspend fun locateAndRunRoot(spec: Spec, test: TestCase): Result<Spec> = runCatching {

      val root = materializer.materialize(spec)
         .firstOrNull { it.descriptor == test.descriptor.root() }
         ?: error("Unable to locate root test ${test.descriptor.path()}")

      logger.log { Pair(spec::class.bestName(), "Searching root '${root.name.testName}' for '${test.name.testName}'") }
      extensions.beforeSpec(spec).getOrThrow()
      locateAndRunRoot(root, test)
      extensions.afterSpec(spec).getOrThrow()
      spec
   }

   private suspend fun locateAndRunRoot(test: TestCase, target: TestCase) {
      logger.log { Pair(test.name.testName, "Executing test in search of target '${target.name.testName}'") }

      coroutineScope {
         val context = object : TestScope {

            var open = true

            override val testCase: TestCase = test
            override val coroutineContext: CoroutineContext = this@coroutineScope.coroutineContext
            override suspend fun registerTestCase(nested: NestedTest) {

               val t = Materializer(configuration).materialize(nested, testCase)
               // if this test is our target then we definitely run it
               // or if the test is on the path to our target we must run it
               if (t.descriptor.isOnPath(target.descriptor)) {
                  open = false
                  seen.add(t.descriptor)
                  locateAndRunRoot(t, target)
                  // otherwise, if we're already past our target we are finding new tests and so
                  // the first new test we run, the rest we queue
               } else if (target.descriptor.isOnPath(t.descriptor)) {
                  if (seen.add(t.descriptor)) {
                     if (open) {
                        open = false
                        locateAndRunRoot(t, target)
                     } else {
                        enqueue(t)
                     }
                  }
               }
            }
         }

         val context2 = DuplicateNameHandlingTestScope(configuration.duplicateTestNameMode, context)

         val testExecutor = TestCaseExecutor(
            object : TestCaseExecutionListener {
               override suspend fun testStarted(testCase: TestCase) {
                  if (started.add(testCase.descriptor)) {
                     logger.log { Pair(test.name.testName, "Notifying test started '${testCase.name.testName}'") }
                     listener.testStarted(testCase)
                  }
               }

               override suspend fun testIgnored(testCase: TestCase, reason: String?) {
                  if (ignored.add(testCase.descriptor))
                     logger.log { Pair(test.name.testName, "Notifying test ignored '${testCase.name.testName}'") }
                  listener.testIgnored(testCase, reason)
               }

               override suspend fun testFinished(testCase: TestCase, result: TestResult) {
                  if (!queue.any { it.testCase.descriptor.isDescendentOf(testCase.descriptor) }) {
                     logger.log { Pair(test.name.testName, "Notifying test finished '${testCase.name.testName}'") }
                     listener.testFinished(testCase, result)
                  }
               }
            },
            defaultCoroutineDispatcherFactory,
            configuration,
         )

         val result = testExecutor.execute(test, context2)
         results[test] = result
      }
   }
}
