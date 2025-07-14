fun IntArray.print() {
    val builder = StringBuilder()
    forEachIndexed { index, i ->
        builder.append(i)
        if (index != lastIndex) {
            builder.append(", ")
        }
    }
    println(builder)
}