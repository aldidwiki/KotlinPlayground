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
