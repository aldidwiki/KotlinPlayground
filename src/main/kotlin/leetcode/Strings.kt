package leetcode

import kotlin.math.abs

fun reverseString() {
    val strings = charArrayOf('H', 'a', 'n', 'n', 'a', 'h')
//    val strings = charArrayOf('h', 'e', 'l', 'l', 'o')

    var left = 0
    var right = strings.size - 1

    while (left < right) {
        val temp = strings[left]
        strings[left] = strings[right]
        strings[right] = temp

        left++
        right--
    }

    println(strings)
}

fun firstUniqueCharacter(): Int {
    val s = "loveleetcode"
    val countMap = mutableMapOf<Char, Int>()

    s.forEach { char ->
        countMap[char] = countMap.getOrDefault(char, 0) + 1
    }

    s.forEachIndexed { index, char ->
        if (countMap[char] == 1) {
            return index
        }
    }

    return -1
}

fun reverseInteger(): Int {
    var x = 1534236469
    var res = 0

    while (x != 0) {
        val digit = x % 10
        x /= 10

        if (abs(res) > Int.MAX_VALUE / 10) return 0

        res = res * 10 + digit
    }

    return res
}

fun validAnagram(): Boolean {
    val s = "car"
    val t = "rat"

    val countMap = mutableMapOf<Char, Int>()
    val countMap2 = mutableMapOf<Char, Int>()

    for (char in s) {
        countMap[char] = countMap.getOrDefault(char, 0) + 1
    }

    for (char in t) {
        countMap2[char] = countMap2.getOrDefault(char, 0) + 1
    }

    println(countMap)
    println(countMap2)

    return countMap == countMap2
}

fun validPalindrome(): Boolean {
    val s = "race a car".filter {
        it.isLetterOrDigit()
    }.lowercase()

    var r = ""
    for (i in s.length - 1 downTo 0) {
        r += s[i]
    }

    println(r)

    return r == s
}

fun implementStr(): Int {
    val haystack = "sadbutsad"
    val needle = "sad"

    val n = haystack.length
    val m = needle.length

    for (i in 0..(n - m)) {
        if (haystack.substring(i, i + m) == needle) {
            return i
        }
    }

    return -1
}

fun myAtoi(): Int {
    val s = "1337c0d3"
    val INT_MAX = Int.MAX_VALUE
    val INT_MIN = Int.MIN_VALUE

    var i = 0
    val n = s.length
    var result = 0
    var sign = 1

    // Step 1: Ignore leading whitespace
    while (i < n && s[i] == ' ') {
        i++
    }

    // Step 2: Check for optional sign
    if (i < n && (s[i] == '+' || s[i] == '-')) {
        sign = if (s[i] == '-') -1 else 1
        i++
    }

    // Step 3: Convert digits to integer
    while (i < n && s[i] in '0'..'9') {
        val digit = s[i] - '0'

        // Check for overflow/underflow before multiplying
        if (result > (INT_MAX - digit) / 10) {
            return if (sign == 1) INT_MAX else INT_MIN
        }

        result = result * 10 + digit
        i++
    }

    return result * sign
}

fun longestCommonPrefix(): String {
    val strs = listOf("flower", "flow", "flight")
    if (strs.isEmpty()) return ""

    var prefix = strs[0]

    for (i in 1 until strs.size) {
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) return ""
        }
    }

    return prefix
}
