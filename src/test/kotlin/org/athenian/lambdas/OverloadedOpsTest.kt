package org.athenian.lambdas

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OverloadedOpsTest {

  @Test
  fun `Console invoke processes non-blank lines and skips blank lines`() {
    val input = "hello\n\nworld\n"
    val originalIn = System.`in`
    val originalOut = System.out
    try {
      System.setIn(ByteArrayInputStream(input.toByteArray()))
      val output = ByteArrayOutputStream()
      System.setOut(PrintStream(output))

      var count = 0
      try {
        Console { line ->
          count++
          if (count >= 2) throw RuntimeException("break")
          "echo: $line"
        }
      } catch (_: RuntimeException) {
      }

      assertThat(output.toString()).contains("echo: hello")
      assertThat(count).isEqualTo(2)
    } finally {
      System.setIn(originalIn)
      System.setOut(originalOut)
    }
  }

  @Test
  fun `main processes all command types`() {
    val input = "go home\ngo\neat pizza\neat\nunknown\n\nquit\n"
    val originalIn = System.`in`
    val originalOut = System.out
    try {
      System.setIn(ByteArrayInputStream(input.toByteArray()))
      val output = ByteArrayOutputStream()
      System.setOut(PrintStream(output))

      try {
        Class.forName("org.athenian.lambdas._7_OverloadedOpsKt")
          .getMethod("main")
          .invoke(null)
      } catch (e: java.lang.reflect.InvocationTargetException) {
        assertThat(e.cause).isInstanceOf(InterruptedException::class.java)
      }

      val outputStr = output.toString()
      assertThat(outputStr).contains("Write your command!")
      assertThat(outputStr).contains("going home")
      assertThat(outputStr).contains("Missing go arg")
      assertThat(outputStr).contains("eating pizza")
      assertThat(outputStr).contains("Missing eat arg")
      assertThat(outputStr).contains("I don't think so...")
    } finally {
      System.setIn(originalIn)
      System.setOut(originalOut)
    }
  }
}
