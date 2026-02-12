package org.athenian.lambdas

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SimpleExampleTest {

  @Test
  fun `constant1 returns 4`() {
    assertThat(constant1()).isEqualTo(4)
    assertThat(constant1.invoke()).isEqualTo(4)
  }

  @Test
  fun `constant2 returns 4`() {
    assertThat(constant2()).isEqualTo(4)
    assertThat(constant2.invoke()).isEqualTo(4)
  }

  @Test
  fun `adder1 adds two integers`() {
    assertThat(adder1(4, 5)).isEqualTo(9)
    assertThat(adder1(0, 0)).isEqualTo(0)
    assertThat(adder1(-1, 1)).isEqualTo(0)
  }

  @Test
  fun `adder2 adds two integers`() {
    assertThat(adder2(4, 5)).isEqualTo(9)
    assertThat(adder2.invoke(4, 5)).isEqualTo(9)
  }

  @Test
  fun `sum adds two integers`() {
    assertThat(sum(5, 6)).isEqualTo(11)
    assertThat(sum(0, 0)).isEqualTo(0)
    assertThat(sum(-3, 3)).isEqualTo(0)
  }

  @Test
  fun `twoIntFunc applies block to two integers`() {
    assertThat(twoIntFunc(5, 6) { x, y -> x * y }).isEqualTo(30)
    assertThat(twoIntFunc(5, 6) { x, y -> x + y }).isEqualTo(11)
    assertThat(twoIntFunc(5, 6) { x, y -> sum(x, y) }).isEqualTo(11)
    assertThat(twoIntFunc(7, 8, ::sum)).isEqualTo(15)
  }

  @Test
  fun `main runs without error`() {
    val originalOut = System.out
    try {
      System.setOut(PrintStream(ByteArrayOutputStream()))
      Class.forName("org.athenian.lambdas._0_SimpleExampleKt")
        .getMethod("main")
        .invoke(null)
    } finally {
      System.setOut(originalOut)
    }
  }
}
