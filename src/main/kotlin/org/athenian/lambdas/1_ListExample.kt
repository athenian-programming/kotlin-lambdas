package org.athenian.lambdas

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

fun lambdaCalculation(inputList: List<Int>): Int =
    inputList
        .takeWhile { it != -1 }
        .filter { it % 2 == 0 }
        .sumOf { it * it }

fun main() {
    val intVals = listOf(0, 2, 5, 6, 4, 7, 8, -1, 12, 14)

    println(nonLambdaCalculation(intVals))
    println(lambdaCalculation(intVals))
}

