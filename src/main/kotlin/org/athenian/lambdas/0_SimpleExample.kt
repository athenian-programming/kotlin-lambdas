package org.athenian.lambdas._7_kotlin_lambdas


val constant = { 4 }

val adder = { a: Int, b: Int -> a + b }

fun sum(x: Int, y: Int) = x + y

fun twoIntFunc(x: Int, y: Int, block: (Int, Int) -> Int): Int = block(x, y)

fun main(args: Array<String>) {

    println(constant.invoke())
    println(constant())

    println(adder.invoke(4, 5))
    println(adder(4, 5))

    print(twoIntFunc(5, 6) { x, y -> x * y })

    print(twoIntFunc(5, 6) { x, y -> sum(x, y) })
    print(twoIntFunc(7, 8, ::sum))
}

