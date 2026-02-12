package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ListExampleTest : FunSpec(
    {

        val intVals = listOf(0, 2, 5, 6, 4, 7, 8, -1, 12, 14)

        test("nonLambdaCalculation computes sum of squares of even numbers before -1") {
            // even numbers before -1: 0, 2, 6, 4, 8 -> 0 + 4 + 36 + 16 + 64 = 120
            nonLambdaCalculation(intVals) shouldBe 120
        }

        test("lambdaCalculation computes same result") {
            lambdaCalculation(intVals) shouldBe 120
        }

        test("both functions produce identical results") {
            nonLambdaCalculation(intVals) shouldBe lambdaCalculation(intVals)
        }

        test("empty list returns 0") {
            nonLambdaCalculation(emptyList()) shouldBe 0
            lambdaCalculation(emptyList()) shouldBe 0
        }

        test("list starting with -1 returns 0") {
            nonLambdaCalculation(listOf(-1, 2, 4)) shouldBe 0
            lambdaCalculation(listOf(-1, 2, 4)) shouldBe 0
        }

        test("list with all odd numbers returns 0") {
            nonLambdaCalculation(listOf(1, 3, 5, 7)) shouldBe 0
            lambdaCalculation(listOf(1, 3, 5, 7)) shouldBe 0
        }

        test("list without -1 processes all elements") {
            // 2^2 + 4^2 = 4 + 16 = 20
            nonLambdaCalculation(listOf(2, 4)) shouldBe 20
            lambdaCalculation(listOf(2, 4)) shouldBe 20
        }

        test("main runs without error") {
            val originalOut = System.out
            try {
                System.setOut(PrintStream(ByteArrayOutputStream()))
                Class.forName("org.athenian.lambdas._1_ListExampleKt")
                    .getMethod("main")
                    .invoke(null)
            } finally {
                System.setOut(originalOut)
            }
        }
    },
)
