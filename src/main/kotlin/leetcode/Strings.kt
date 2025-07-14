package leetcode

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
    var x = 123

    return x
}