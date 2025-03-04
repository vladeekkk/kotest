---
id: genslist
title: Generators List
slug: property-test-generators-list.html
sidebar_label: Generators List
---

This page lists all current generators in Kotest. There are two types of generator: [arbitrary](gens.md#arbitrary)
and [exhaustive](gens.md#exhaustive).

Most generators are available on all platforms. Some are JVM or JS specific.

We also provide generators for [Arrow](arrow.md) as a separate module.

| Generator    | Description | JVM | JS | Native |
| -------- | ----------- | --- | --- | ------ |
| **Nulls** |
| `arb.orNull()` | Generates random values from the arb instance, with null values mixed in. For example, `Arb.int().orNull()` could generate `1, -1, null, 8, 17`, and so on. Has overloaded versions to control the frequency of nulls being generated.| ✓ | ✓ | ✓ |
| `arb.orNull(nullProbability)` | Generates random values from the arb instance, with null values mixed in using the defined probability. | ✓ | ✓ | ✓ |
| **Booleans** |
| `Arb.boolean()` | Returns an `Arb` that produces `Boolean`s. | ✓ | ✓ | ✓ |
| `Arb.booleanArray(length, content)` | Returns an `Arb` that produces `BoolArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| `Exhaustive.boolean()` | Alternatives between true and false. | ✓ | ✓ | ✓ |
| **Chars** |
| `Arb.char(range1, range2,...)` | Returns random char's generated from one or more given ranges. By supporting multiple ranges, it is possible to specific non-consecutive ranges of characters to populate values from. | ✓ | ✓ | ✓ |
| `Arb.char(List<CharRange>)` | Returns chars distributed across the lists of chars. For example, `Arb.char(listOf('A'..'C', 'X'..'Z'))` will generate values of A, B, C, X, Y, Z with equal probability.
| `Arb.charArray(length, content)` | Returns an `Arb` that produces `CharArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| **Constants** |
| `Arb.constant(t)` | Returns an `Arb` that always returns `t` | ✓ | ✓ | ✓ |
| **Bytes** |
| `Arb.byte(min, max)` | Returns an `Arb` that produces `Byte`s from `min` to `max` (inclusive). The edge cases are `min`, -1, 0, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.positiveByte(min, max)` | Returns an `Arb` that produces positive `Byte`s from `min` to `max` (inclusive). The edge cases are 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.negativeByte(min, max)` | Returns an `Arb` that produces negative `Byte`s from `min` to `max` (inclusive). The edge cases are `min` and -1 which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.byteArray(length, content)` | Returns an `Arb` that produces `ByteArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| `Arb.uByte(min, max)` | Returns an `Arb` that produces `UByte`s from `min` to `max` (inclusive). The edge cases are `min`, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.uByteArray(length, content)` | Returns an `Arb` that produces `UByteArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| **Shorts** |
| `Arb.short(min, max)` | Returns an `Arb` that produces `Short`s from `min` to `max` (inclusive). The edge cases are `min`, -1, 0, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.positiveShort(min, max)` | Returns an `Arb` that produces positive `Short`s from `min` to `max` (inclusive). The edge cases are 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.negativeShort(min, max)` | Returns an `Arb` that produces negative `Short`s from `min` to `max` (inclusive). The edge cases are `min` and -1 which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.shortArray(length, content)` | Returns an `Arb` that produces `ShortArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| `Arb.uShort(min, max)` | Returns an `Arb` that produces `UShort`s from `min` to `max` (inclusive). The edge cases are `min`, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.uShortArray(length, content)` | Returns an `Arb` that produces `UShortArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| **Ints** |
| `Arb.int(min, max)` | Returns an `Arb` that produces `Int`s from `min` to `max` (inclusive). The edge cases are `min`, -1, 0, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.positiveInt(min, max)` | Returns an `Arb` that produces positive `Int`s from `min` to `max` (inclusive). The edge cases are 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.nonNegativeInt(min, max)` | Returns an `Arb` that produces non negative `Int`s from `min` to `max` (inclusive). The edge cases are 0, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.negativeInt(min, max)` | Returns an `Arb` that produces negative `Int`s from `min` to `max` (inclusive). The edge cases are `min` and -1 which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.nonPositiveInt(min, max)` | Returns an `Arb` that produces non positive `Int`s from `min` to `max` (inclusive). The edge cases are `min`, -1 and 0 which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.intArray(length, content)` | Returns an `Arb` that produces `IntArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| `Arb.uInt(min, max)` | Returns an `Arb` that produces `UInt`s from `min` to `max` (inclusive). The edge cases are `min`, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.uIntArray(length, content)` | Returns an `Arb` that produces `UIntArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| `Exhaustive.ints(range)` | Returns all ints in the given range. | ✓ | ✓ | ✓ |
| `Arb.multiple(k, max)` | Generates multiples of k up a max value. The edge cases are `0`. | ✓ | ✓ | ✓ |
| `Arb.factor(k)` | Generates factors of k. | ✓ | ✓ | ✓ |
| **Longs** |
| `Arb.long(min, max)` | Returns an `Arb` that produces `Long`s from `min` to `max` (inclusive). The edge cases are `min`, -1, 0, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.positiveLong(min, max)` | Returns an `Arb` that produces positive `Long`s from `min` to `max` (inclusive). The edge cases are 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.negativeLong(min, max)` | Returns an `Arb` that produces negative `Long`s from `min` to `max` (inclusive). The edge cases are `min` and -1 which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.longArray(length, content)` | Returns an `Arb` that produces `LongArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| `Arb.uLong(min, max)` | Returns an `Arb` that produces `ULong`s from `min` to `max` (inclusive). The edge cases are `min`, 1 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.uLongArray(length, content)` | Returns an `Arb` that produces `ULongArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| `Exhaustive.longs(range)` | Returns all longs in the given range. | ✓ | ✓ | ✓ |
| **Floats** |
| `Arb.float(min, max)` | Returns an `Arb` that produces `Float`s from `min` to `max` (inclusive). The edge cases are `Float.NEGATIVE_INFINITY`, `min`, -1.0, -`Float.MIN_VALUE`, -0.0, 0.0, `Float.MIN_VALUE`, 1.0, `max`, `Float.POSITIVE_INFINITY` and `Float.NaN` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.positiveFloat(min, max)` | Returns an `Arb` that produces positive `Float`s from `min` to `max` (inclusive). `Float.MIN_VALUE`, 1.0, `max`, `Float.POSITIVE_INFINITY` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.negativeFloat(min, max)` | Returns an `Arb` that produces negative `Float`s from `min` to `max` (inclusive). The edge cases are `Float.NEGATIVE_INFINITY`, `min`, -1.0 and -`Float.MIN_VALUE` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.numericFloat(min, max)` | Returns an `Arb` that produces numeric `Float`s from `min` to `max` (inclusive). The edge cases are `min`, -1.0, -`Float.MIN_VALUE`, -0.0, 0.0, `Float.MIN_VALUE`, 1.0 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.floatArray(length, content)` | Returns an `Arb` that produces `FloatArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| **Doubles** |
| `Arb.double(min, max)` | Returns an `Arb` that produces `Double`s from `min` to `max` (inclusive). The edge cases are `Double.NEGATIVE_INFINITY`, `min`, -1.0, -`Double.MIN_VALUE`, -0.0, 0.0, `Double.MIN_VALUE`, 1.0, `max`, `Double.POSITIVE_INFINITY` and `Double.NaN` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.positiveDouble(min, max)` | Returns an `Arb` that produces positive `Double`s from `min` to `max` (inclusive). `Double.MIN_VALUE`, 1.0, `max`, `Double.POSITIVE_INFINITY` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.negativeDouble(min, max)` | Returns an `Arb` that produces negative `Double`s from `min` to `max` (inclusive). The edge cases are `Double.NEGATIVE_INFINITY`, `min`, -1.0 and -`Double.MIN_VALUE` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.numericDouble(min, max)` | Returns an `Arb` that produces numeric `Double`s from `min` to `max` (inclusive). The edge cases are `min`, -1.0, -`Double.MIN_VALUE`, -0.0, 0.0, `Double.MIN_VALUE`, 1.0 and `max` which are only included if they are in the provided range. | ✓ | ✓ | ✓ |
| `Arb.doubleArray(length, content)` | Returns an `Arb` that produces `DoubleArray`s where `length` produces the length of the arrays and `content` produces the content of the arrays. | ✓ | ✓ | ✓ |
| **Enums** |
| `Arb.enum<T>()` | Randomly selects constants from the given enum. | ✓ | ✓ | ✓ |
| `Exhaustive.enum<T>()` | Iterates all the constants defined in the given enum. | ✓ | ✓ | ✓ |
| **Regional** |
| `Arb.locale()` | Generates locales in the Java format, eg `en_US` or `ca_ES_VALENCIA`. | ✓ | ✓ | ✓ |
| `Arb.timezoneCodeThree()` | Generates timezones in the format ABC, for example BST or EST. Does not include all possible timezones, and is only used for sampling data. | ✓ | ✓ | ✓ |
| `Arb.geoLocation()` | Generates `GeoLocation` objects with random latitude/longitude points uniformly distributed on the globe. | ✓ | ✓ | ✓ |
| **Strings** |
| `Arb.string(range)` | Generates random printable strings with a randomly chosen size from the given range. If range is not specified then (0..100) is used. The edge cases include empty string, a blank string and a unicode string. | ✓ | ✓ | ✓ |
| `Arb.stringPattern(pattern)` | Generates strings that match given pattern using [Generex](https://github.com/mifmif/Generex) | ✓ |   |   |
| `Exhaustive.azstring(range)` | Returns all A-Z strings in the given range. For example if range was 1..2 then a, b, c, ...., yz, zz would be included. | ✓ | ✓ | ✓ |
| `Arb.email(localPartGen, domainGen)` | Generates random emails where the local part and domain part are random strings generated by the given generators. A default value is provided for both. | ✓ | ✓ | ✓ |
| `Arb.emailLocalPart()` | Generates random local email parts | ✓ | ✓ | ✓ |
| `Arb.uuid(type)` | Generates random UUIDs of the given type | ✓ |  |  |
| `Arb.domain(tlds, labelArb)` | Generates random domains with a random tld (defaults to any of the top 120 TLDs) and a label generator, which generates domain parts. | ✓ | ✓ | ✓ |
| **Builders** |
| `Arb.create(fn)` | Generates values using the supplied function. | ✓ | ✓ | ✓ |
| `Arb.bind(arbA, arbB, fn)` | Generates values by pulling a value from each of the two given arbs and then passing those values to the supplied function. | ✓ | ✓ | ✓ |
| `Arb.bind(arbA, arbB, arbC, fn)` | Generates values by pulling a value from each of the three given arbs and then passing those values to the supplied function. | ✓ | ✓ | ✓ |
| `Arb.bind(arbA, ...., fn)` | Generates values by pulling a value from each of the given arbs and then passing those values to the supplied function. | ✓ | ✓ | ✓ |
| **Combinatorics** |
| `Arb.choice(arbs)` | Randomly selects one of the given arbs and then uses that to generate the next element.  | ✓ | ✓ | ✓ |
| `Arb.choose(pairs)` | Generates values based on weights. For example, `Arb.choose(1 to 'A', 2 to 'B')` will generate 'A' 33% of the time and 'B' 66% of the time. | ✓ | ✓ | ✓ |
| `Arb.frequency(list)` | Alias to choose | ✓ | ✓ | ✓ |
| `Arb.shuffle(list)` | Generates random permutations of a list. For example, `Arb.shuffle(listOf(1,2,3))` could generate `listOf(3,1,2)`, `listOf(1,3,2)` and so on. | ✓ | ✓ | ✓ |
| `Arb.subsequence(list)` | Generates a random subsequence of the given list starting at index 0 and including the empty list. For example, `Arb.subsequence(listOf(1,2,3))` could generate `listOf(1)`, `listOf(1,2)`, and so on. | ✓ | ✓ | ✓ |
| **Collections** |
| `Arb.element(collection)` | Randomly selects one of the elements of the given collection. | ✓ | ✓ | ✓ |
| `Arb.element(vararg T)` | Randomly selects one of the elements from the varargs. | ✓ | ✓ | ✓ |
| `Arb.list(gen, range)` | Generates lists where values are generated by the given element generator. The size of each list is determined randomly by the specified range. | ✓ | ✓ | ✓ |
| `Arb.set(gen, range)` | Generates sets where values are generated by the given element generator. The size of each set is determined randomly by the specified range. The slippage argument specifies how many attempts will be made to generate each element before erroring, in the case that the underlying arb does not have enough unique values to satisfy the set size. | ✓ | ✓ | ✓ |
| `Arb.set(gen, range, slippage)` | Generates sets where values are generated by the given element generator. The size of each set is determined randomly by the specified range. | ✓ | ✓ | ✓ |
| `Arb<T>.chunked(range)` | Generates lists where each list is populated from elements of this receiver. The size of each size is randomly chosen within the given range. | ✓ | ✓ | ✓ |
| `Arb<T>.chunked(minSize, maxSize)` | Generates lists where each list is populated from elements of this receiver. The size of each size is randomly chosen within the given range parameters. | ✓ | ✓ | ✓ |
| `Exhaustive.collection(list)` | Enumerates each element of the list one by one. | ✓ | ✓ | ✓ |
| **Maps**|
| `Arb.map(Arb<Pair<K,V>>, minSize, maxSize)` | Generates random maps, each one with a size between minSize and maxSize, and each element generated from the given Pair arb. | ✓ | ✓ | ✓ |
| `Arb.map(Arb<K>, Arb<V>, minSize, maxSize)` | Generates random maps, each one with a size between minSize and maxSize, and each key generated from the given key arb and each value generated from the given value arb. | ✓ | ✓ | ✓ |
| **Tuples**|
| `Arb.pair(arb1, arb2)` | Generates `Pair` instances where each value of the pair is drawn from the two provided arbs | ✓ | ✓ | ✓ |
| `Arb.triple(arb1, arb2, arb3)` | Generates `Triple` instances where each value of the triple is drawn from the three provided arbs | ✓ | ✓ | ✓ |
| **Dates** |
| `Arb.date(ranges)` | Generates random dates with the year between the given range |  | ✓ |  |
| `Arb.datetime(ranges)` | Generates random date times with the year between the given range |  | ✓ |  |
| `Arb.localDateTime(ranges)` | Generates random LocalDateTime's with the year between the given range | ✓ |  |  |
| `Arb.localDate(ranges)` | Generates random LocalDate's with the year between the given range | ✓ |  |  |
| **Durations** |
| `Arb.duration(ranges)` | Generates random durations in the given range. | ✓ | ✓ | ✓ |
| **Kotlinx DateTime** | Requires `io.kotest.extensions:kotest-property-datetime` module |
| `Arb.date(yearRange)` | Generates `LocalDate`s with the year between the given range and other fields randomly. | ✓ | ✓ | ✓ |
| `Arb.datetime(yearRange, hourRange, minuteRange, secondRage)` | Generates `LocalDateTime`s with all fields in the given ranges | ✓ | ✓ | ✓ |
| `Arb.instant(range)` | Generates `Instant`s with the epoch randomly generated in the given range | ✓ | ✓ | ✓ |
| **Networking**|
| `Arb.ipAddressV4()` | Generates random IP addresses in the format a.b.c.d, where each part is between 0 and 255. | ✓ | ✓ | ✓ |
