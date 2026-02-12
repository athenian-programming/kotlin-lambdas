package org.athenian.lambdas

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScopeFunctionsTest {

  private val sft = ScopeFunctionTest()

  private fun <T> suppressOutput(block: () -> T): T {
    val originalOut = System.out
    try {
      System.setOut(PrintStream(ByteArrayOutputStream()))
      return block()
    } finally {
      System.setOut(originalOut)
    }
  }

  @Test
  fun `letExample returns lambda result`() {
    assertThat(suppressOutput { sft.letExample() }).isEqualTo(42)
  }

  @Test
  fun `alsoExample returns the original string`() {
    assertThat(suppressOutput { sft.alsoExample() }).isEqualTo("StringVal")
  }

  @Test
  fun `runExample returns lambda result`() {
    assertThat(suppressOutput { sft.runExample() }).isEqualTo(42)
  }

  @Test
  fun `applyExample returns the original string`() {
    assertThat(suppressOutput { sft.applyExample() }).isEqualTo("StringVal")
  }

  @Test
  fun `withExample returns lambda result`() {
    assertThat(suppressOutput { sft.withExample() }).isEqualTo(42)
  }

  @Test
  fun `main runs without error`() {
    suppressOutput {
      Class.forName("org.athenian.lambdas._3_ScopeFunctionsKt")
        .getMethod("main")
        .invoke(null)
    }
  }
}
