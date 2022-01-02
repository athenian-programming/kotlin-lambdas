package org.athenian.lambdas

class ScopeFunctionTest {

    fun letExample(): Int {
        val str = "StringVal"
        return str.let {
            println("this: $this")
            println("it: $it")
            42
        }
    }

    fun alsoExample(): String {
        val str = "StringVal"
        return str.also {
            println("this: $this")
            println("it: $it")
            42
        }
    }

    fun runExample(): Int {
        val str = "StringVal"
        return str.run {
            println("this: $this")
            //println("it: $it")
            42
        }
    }

    fun applyExample(): String {
        val str = "StringVal"
        return str.apply {
            println("this: $this")
            //println("it: $it")
            42
        }
    }

    fun withExample(): Int {
        val str = "StringVal"
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