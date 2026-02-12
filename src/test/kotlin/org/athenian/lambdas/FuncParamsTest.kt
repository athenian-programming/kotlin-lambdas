package org.athenian.lambdas

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FuncParamsTest {

  @Test
  fun `createString builds string using StringBuilder receiver`() {
    val result = createString {
      append("Hello")
      append(" ")
      append("World")
    }
    assertThat(result).isEqualTo("Hello World")
  }

  @Test
  fun `createString with numbers and text`() {
    val result = createString {
      append(4)
      append("Hello World")
    }
    assertThat(result).isEqualTo("4Hello World")
  }

  @Test
  fun `createString with empty block returns empty string`() {
    val result = createString {}
    assertThat(result).isEmpty()
  }

  @Test
  fun `main runs without error`() {
    val originalOut = System.out
    try {
      System.setOut(PrintStream(ByteArrayOutputStream()))
      Class.forName("org.athenian.lambdas._2_FuncParamsKt")
        .getMethod("main")
        .invoke(null)
    } finally {
      System.setOut(originalOut)
    }
  }
}
