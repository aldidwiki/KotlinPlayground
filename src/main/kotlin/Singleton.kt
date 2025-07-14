object Singleton {
    init {
        println("This is init of Singleton")
    }

    fun printSingleton(text: String) {
        println("This is singleton: $text")
    }
}

class Global private constructor() {
    private var i = 0

    fun printSomething() {
        i++
        println("This is Global class: $i")
    }

    companion object {
        // singleton method
        val INSTANCE: Global by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Global() }

        // factory method
        fun getInstance(): Global = Global()
    }
}