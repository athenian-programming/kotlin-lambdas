package org.athenian.lambdas

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HofsTest {

  private fun captureOutput(block: () -> Unit): String {
    val originalOut = System.out
    val output = ByteArrayOutputStream()
    try {
      System.setOut(PrintStream(output))
      block()
    } finally {
      System.setOut(originalOut)
    }
    return output.toString()
  }

  @Test
  fun `functionLiteral prints expected values`() {
    val result = captureOutput { functionLiteral() }
    assertThat(result).contains("5.0")
    assertThat(result).contains("Umberto")
    assertThat(result).contains("9") // square(3) = 9
  }

  @Test
  fun `hofLiteral prints expected values`() {
    val result = captureOutput { hofLiteral() }
    assertThat(result).contains("25")  // apply5 { it * it } = 25
    assertThat(result).contains("11")  // applySum(4)(7) = 11
    assertThat(result).contains("-25") // applyInverse { it * it }(5) = -25
  }

  @Test
  fun `functionLiteralsWithReceiver prints expected values`() {
    val result = captureOutput { functionLiteralsWithReceiver() }
    assertThat(result).contains("25") // square(5) = 25
    assertThat(result).contains("50") // squared(5) { it * 2 } = 50
  }

  @Test
  fun `main runs without error`() {
    captureOutput {
      Class.forName("org.athenian.lambdas._4_HofsKt")
        .getMethod("main")
        .invoke(null)
    }
  }
}
