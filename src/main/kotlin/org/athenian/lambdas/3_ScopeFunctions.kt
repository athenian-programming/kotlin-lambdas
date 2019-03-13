package org.athenian.lambdas._7_kotlin_lambdas

class ScopeFunctionTest {

    fun letExample(): Int {
        val str: String = "StringVal"
        return str.let {
            println("this: $this")
            println("it: $it")
            42
        }
    }

    fun alsoExample(): String {
        val str: String = "StringVal"
        return str.also {
            println("this: $this")
            println("it: $it")
            42
        }
    }

    fun runExample(): Int {
        val str: String = "StringVal"
        return str.run {
            println("this: $this")
            //println("it: $it")
            42
        }
    }

    fun applyExample(): String {
        val str: String = "StringVal"
        return str.apply {
            println("this: $this")
            //println("it: $it")
            42
        }
    }

    fun withExample(): Int {
        val str: String = "StringVal"
        return with(str) {
            println("this: $this")
            //println("it: $it")
            42
        }
    }
}

fun main() {
    val obj = ScopeFunctionTest()

    println("let:")
    println("return: ${obj.letExample()}")

    println("\nalso:")
    println("return: ${obj.alsoExample()}")

    println("\nrun:")
    println("return: ${obj.runExample()}")

    println("\napply:")
    println("return: ${obj.applyExample()}")

    println("\nwith:")
    println("return: ${obj.withExample()}")
}
