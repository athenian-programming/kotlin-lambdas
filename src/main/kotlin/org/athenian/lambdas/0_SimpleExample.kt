package org.athenian.lambdas


// Lambda without params
val constant1: () -> Int = fun(): Int { return 4 }
val constant2: () -> Int = { 4 }

// Lambda with params
val adder1: (Int, Int) -> Int = fun(a: Int, b: Int): Int { return a + b }
val adder2: (Int, Int) -> Int = { a: Int, b: Int -> a + b }

fun sum(x: Int, y: Int): Int = x + y

fun twoIntFunc(x: Int, y: Int, block: (Int, Int) -> Int): Int = block(x, y)

fun main() {

    println(constant2.invoke())
    println(constant2())

    println(adder2.invoke(4, 5))
    println(adder2(4, 5))

    println(twoIntFunc(5, 6) { x, y -> x * y })

    println(twoIntFunc(5, 6) { x, y -> sum(x, y) })
    println(twoIntFunc(7, 8, ::sum))
}

