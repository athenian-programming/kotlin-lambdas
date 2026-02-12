package org.athenian.lambdas

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ListExampleTest {

  private val intVals = listOf(0, 2, 5, 6, 4, 7, 8, -1, 12, 14)

  @Test
  fun `nonLambdaCalculation computes sum of squares of even numbers before -1`() {
    // even numbers before -1: 0, 2, 6, 4, 8 → 0 + 4 + 36 + 16 + 64 = 120
    assertThat(nonLambdaCalculation(intVals)).isEqualTo(120)
  }

  @Test
  fun `lambdaCalculation computes same result`() {
    assertThat(lambdaCalculation(intVals)).isEqualTo(120)
  }

  @Test
  fun `both functions produce identical results`() {
    assertThat(nonLambdaCalculation(intVals)).isEqualTo(lambdaCalculation(intVals))
  }

  @Test
  fun `empty list returns 0`() {
    assertThat(nonLambdaCalculation(emptyList())).isEqualTo(0)
    assertThat(lambdaCalculation(emptyList())).isEqualTo(0)
  }

  @Test
  fun `list starting with -1 returns 0`() {
    assertThat(nonLambdaCalculation(listOf(-1, 2, 4))).isEqualTo(0)
    assertThat(lambdaCalculation(listOf(-1, 2, 4))).isEqualTo(0)
  }

  @Test
  fun `list with all odd numbers returns 0`() {
    assertThat(nonLambdaCalculation(listOf(1, 3, 5, 7))).isEqualTo(0)
    assertThat(lambdaCalculation(listOf(1, 3, 5, 7))).isEqualTo(0)
  }

  @Test
  fun `list without -1 processes all elements`() {
    // 2^2 + 4^2 = 4 + 16 = 20
    assertThat(nonLambdaCalculation(listOf(2, 4))).isEqualTo(20)
    assertThat(lambdaCalculation(listOf(2, 4))).isEqualTo(20)
  }

  @Test
  fun `main runs without error`() {
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
}
