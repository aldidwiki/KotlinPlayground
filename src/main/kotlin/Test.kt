import java.text.NumberFormat
import java.util.*

/** max 1_000_000 trillion, Long limitation*/
fun Long.convertNominalToAlphabet(): String {
    val divider = this / 1000

    var multiply = if (divider < 1) { // for nominal below 1000
        0L
    } else {
        1L
    }
    for (i in 1 until divider.toString().length) {
        multiply *= 10
    }

    val thousandDivider = multiply / 1
    val millionDivider = multiply / 1000
    val billionDivider = multiply / 1_000_000
    val trillionDivider = multiply / 1_000_000_000

    return when {
        thousandDivider in 1..100 -> "$thousandDivider ribu"
        millionDivider in 1..100 -> "$millionDivider juta"
        billionDivider in 1..100 -> "$billionDivider milyar"
        trillionDivider >= 1 -> "$trillionDivider trilyun"
        else -> this.toString()
    }
}

fun String.formatToCurrency(isNeedDecimal: Boolean): String {
    return NumberFormat.getInstance(Locale("en", "US")).parse(this).let {
        println(it)
        NumberFormat.getCurrencyInstance(Locale("id", "ID")).apply {
            currency = Currency.getInstance("IDR")
            minimumFractionDigits = 0
            maximumFractionDigits = 2
        }.format(
            if (isNeedDecimal) it.toDouble() else it.toLong()
        )
    }
}

/*Buatlah fungsi untuk menentukan apakah sebuah ekspresi matematika mengandung duplikat tanda kurung

Input: "((a+b))"
Output: true  // Ada duplikat

Input: "(a+(b)/c)"
Output: false // Tidak ada duplikat

Input: "(a+b*(c-d))"
Output: false // Tidak ada duplikat

Input: "(a)+((b))"
Output: true  // ((b)) dianggap duplikat*/

fun checkParenthesesDuplicate(s: String): Boolean {
    var parentheses = 0
    s.forEachIndexed { index, c ->
        if (c == '(') {
            if (s[index + 1] == '(') {
                parentheses++
            }
        }

        if (c == ')' && parentheses > 0 && s.lastIndex != index) {
            if (s[index + 1] == ')') {
                return true
            }
        }
    }

    return false
}

fun selectionSort() {
    val numbers = mutableListOf(4, 2, 6, 1, 7)
    for (i in 0 until numbers.size) {

        var minIndex = i

        for (j in i + 1 until numbers.size) {
            if (numbers[j] < numbers[minIndex]) {
                minIndex = j
            }
        }

        val temp = numbers[i]
        numbers[i] = numbers[minIndex]
        numbers[minIndex] = temp
    }

    println(numbers)
}