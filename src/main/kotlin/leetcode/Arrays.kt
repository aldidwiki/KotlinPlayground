package leetcode

import print

fun rotateArray() {
    val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7)
//    val numbers = intArrayOf(-1, -100, 3, 99)
    val n = numbers.size
    val k = 2

    for (i in 0 until k) {

        val last = numbers[n - 1]
        for (j in n - 1 downTo 1) {
            numbers[j] = numbers[j - 1]
        }
        numbers[0] = last
    }

    numbers.forEach {
        print(it)
    }
}

fun containsDuplicate(): Boolean {
    val numbers = intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)

    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {
            if (numbers[i] == numbers[j]) {
                return true
            }
        }
    }

    return false
}

fun singleNumber(): Int {
    val numbers = intArrayOf(4, 1, 2, 1, 2)
    val countMap = mutableMapOf<Int, Int>()

    for (num in numbers) {
        countMap[num] = countMap.getOrDefault(num, 0) + 1
    }

    numbers.forEach { num ->
        if (countMap[num] == 1) {
            return num
        }
    }

    return 0
}

fun removeDuplicatesFromSortedArray(): Int {
    val numbers = intArrayOf(1, 1, 2)
    val countMap = mutableMapOf<Int, Int>()

    for (num in numbers) {
        countMap[num] = countMap.getOrDefault(num, 0) + 1
    }

    for ((i, num) in countMap.keys.withIndex()) {
        numbers[i] = num
    }

    return countMap.keys.size
}

fun bestTimeToBuyAndSellStockII(): Int {
    val prices = intArrayOf(1, 2, 3, 4, 5)
    val profit = mutableListOf<Int>()

    for (i in prices.indices) {
        for (j in i + 1 until prices.size) {
            if (prices[j] > prices[i]) {
                profit.add(prices[j] - prices[i])
            }
            break
        }
    }

    return profit.sum()
}

fun plusOne(): IntArray {
    val digits = intArrayOf(1, 2, 2)
    val newDigits: IntArray
    val n = digits.size

    for (i in n - 1 downTo 0) {
        if (digits[i] == 9) {
            digits[i] = 0
        } else {
            digits[i] += 1
            break
        }
    }

    if (digits[0] == 0) {
        newDigits = digits + 1
        val temp = newDigits[0]
        newDigits[0] = newDigits[newDigits.size - 1]
        newDigits[newDigits.size - 1] = temp
    } else {
        newDigits = digits
    }

    newDigits.forEach {
        print(it)
    }

    return digits
}

fun twoSum(): IntArray {
    val numbers = intArrayOf(3, 3)
    val target = 6
    var indices = intArrayOf()

    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {
            if (numbers[i] + numbers[j] == target) {
                indices = intArrayOf(i, j)
            }
        }
    }

    indices.forEach {
        print(it)
    }

    return indices
}

fun moveZeroes() {
    val numbers = intArrayOf(0, 0, 1, 2, 3)
    var zeroIndex = 0

    for (i in numbers.indices) {
        if (numbers[i] != 0) {
            val temp = numbers[zeroIndex]
            numbers[zeroIndex] = numbers[i]
            numbers[i] = temp
            zeroIndex++
        }
    }

    numbers.forEach {
        print("$it,")
    }
}

fun intersectionOfTwoArraysII() {
    val numbers1 = intArrayOf(4, 9, 5)
    val numbers2 = intArrayOf(9, 4, 9, 8, 4)
    var intersects = intArrayOf()
    val countMap = mutableMapOf<Int, Int>()

    for (num in numbers1) {
        countMap[num] = countMap.getOrDefault(num, 0) + 1
    }

    for (num in numbers2) {
        if (countMap.getOrDefault(num, 0) > 0) {
            intersects += num
            countMap[num] = countMap.getOrDefault(num, 0) - 1
        }
    }

    println(countMap)
    intersects.print()
}

fun validSudoku(): Boolean {
    val board = listOf(
        listOf("5", "3", ".", ".", "7", ".", ".", ".", "."),
        listOf("6", ".", ".", "1", "9", "5", ".", ".", "."),
        listOf(".", "9", "8", ".", ".", ".", ".", "6", "."),
        listOf("8", ".", ".", ".", "6", ".", ".", ".", "3"),
        listOf("4", ".", ".", "8", ".", "3", ".", ".", "1"),
        listOf("7", ".", ".", ".", "2", ".", ".", ".", "6"),
        listOf(".", "6", ".", ".", ".", ".", "2", "8", "."),
        listOf(".", ".", ".", "4", "1", "9", ".", ".", "5"),
        listOf(".", ".", ".", ".", "8", ".", ".", "7", "9"),
    )

//    val board = listOf(
//        listOf(".", ".", ".", ".", "5", ".", ".", "1", "."),
//        listOf(".", "4", ".", "3", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", "3", ".", ".", "1"),
//        listOf("8", ".", ".", ".", ".", ".", ".", "2", "."),
//        listOf(".", ".", "2", ".", "7", ".", ".", ".", "."),
//        listOf(".", "1", "5", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", "2", ".", ".", "."),
//        listOf(".", "2", ".", "9", ".", ".", ".", ".", "."),
//        listOf(".", ".", "4", ".", ".", ".", ".", ".", ".")
//    )

//    val board = listOf(
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
//        listOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
//    )

    val rows = MutableList(9) { mutableSetOf<String>() }
    val cols = MutableList(9) { mutableSetOf<String>() }
    val box = MutableList(9) { mutableSetOf<String>() }

    for (i in board.indices) {
        for (j in board.indices) {
            val num = board[i][j]
            if (num == ".") continue

            println(rows[i])

            val boxIndex = (i / 3) * 3 + (j / 3)
            if (num in rows[i] || num in cols[j] || num in box[boxIndex]) {
                return false
            }

            rows[i].add(num)
            cols[j].add(num)
            box[boxIndex].add(num)
        }
    }

    return true
}

fun rotateImage() {
    val matrix = mutableListOf(
        mutableListOf(1, 2, 3),
        mutableListOf(4, 5, 6),
        mutableListOf(7, 8, 9),
    )

    for (i in matrix.indices) {
        for (j in i + 1 until matrix.size) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
        }
    }

    for (i in matrix.indices) {
        matrix[i].reverse()
    }

    println(matrix)
}