package org.athenian.lambdas


val constant: () -> Int = { 4 }

val adder: (Int, Int) -> Int = { a: Int, b: Int -> a + b }

fun sum(x: Int, y: Int): Int = x + y

fun twoIntFunc(x: Int, y: Int, block: (Int, Int) -> Int): Int = block(x, y)

fun main() {

    println(constant.invoke())
    println(constant())

    println(adder.invoke(4, 5))
    println(adder(4, 5))

    println(twoIntFunc(5, 6) { x, y -> x * y })

    println(twoIntFunc(5, 6) { x, y -> sum(x, y) })
    println(twoIntFunc(7, 8, ::sum))
}

