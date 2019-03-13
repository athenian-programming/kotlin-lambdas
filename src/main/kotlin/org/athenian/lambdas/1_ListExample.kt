package org.athenian.lambdas._7_kotlin_lambdas


fun nonLambdaCalculation(inputList: List<Int>): Int {
    var result = 0
    for (input in inputList) {
        if (input == -1) break
        if (input % 2 == 0) {
            result += input * input
        }
    }
    return result
}

fun lambdaCalculation(inputList: List<Int>) =
    inputList
        .takeWhile { it != -1 }
        .filter { it % 2 == 0 }
        .map { it * it }
        .sum()

fun main(args: Array<String>) {
    val intVals = listOf(0, 2, 5, 6, 4, 7, 8, -1, 12, 14)

    println(nonLambdaCalculation(intVals))
    println(lambdaCalculation(intVals))
}

