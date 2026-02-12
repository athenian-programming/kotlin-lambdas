package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeInstanceOf
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OverloadedOpsTest : FunSpec(
  {

    test("Console invoke processes non-blank lines and skips blank lines") {
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

        output.toString() shouldContain "echo: hello"
        count shouldBe 2
      } finally {
        System.setIn(originalIn)
        System.setOut(originalOut)
      }
    }

    test("main processes all command types") {
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
          e.cause.shouldBeInstanceOf<InterruptedException>()
        }

        val outputStr = output.toString()
        outputStr shouldContain "Write your command!"
        outputStr shouldContain "going home"
        outputStr shouldContain "Missing go arg"
        outputStr shouldContain "eating pizza"
        outputStr shouldContain "Missing eat arg"
        outputStr shouldContain "I don't think so..."
      } finally {
        System.setIn(originalIn)
        System.setOut(originalOut)
      }
    }
  },
)
