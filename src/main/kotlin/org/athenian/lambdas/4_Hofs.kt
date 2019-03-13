package org.athenian.lambdas._7_kotlin_lambdas

fun main() {
    functionLiteral()
    hofLiteral()
    functionLiteralsWithReceiver()
}

fun functionLiteral() {
    val x: Double = 5.0
    val name: String = "Uberto"

    //Function literal
    val square: (Int) -> Int = { it * it }

    println(x)
    println(name)
    println(square(3))
}

fun hofLiteral() {
    //Higher Order Function Literals
    val apply5: ((Int) -> Int) -> Int = { it(5) }
    val applySum: (Int) -> ((Int) -> Int) = { x -> { it + x } }
    val applyInverse: ((Int) -> Int) -> ((Int) -> Int) = { f -> { -1 * f(it) } }

    println(apply5 { it * it })
    println(applySum(4)(7))
    println(applyInverse { it * it }(5))
}

fun functionLiteralsWithReceiver() {
    fun square(x: Int): Int = x * x
    fun squared(a: Int, f: (Int) -> Int): Int = f(square(a))

    // Extension function
    fun Int.square(): Int = this * this

    // Function Literals with a Receiver
    fun squared2(a: Int, f: Int.() -> Int): Int = f(square(a))

    println(square(5))
    println(squared(5) { it * 2 })

    println(5.square())
    println(squared2(5) { this * 2 })
}