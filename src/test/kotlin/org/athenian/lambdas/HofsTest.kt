package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class HofsTest : FunSpec(
  {

    fun captureOutput(block: () -> Unit): String {
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

    test("functionLiteral prints expected values") {
      val result = captureOutput { functionLiteral() }
      result shouldContain "5.0"
      result shouldContain "Umberto"
      result shouldContain "9" // square(3) = 9
    }

    test("hofLiteral prints expected values") {
      val result = captureOutput { hofLiteral() }
      result shouldContain "25"  // apply5 { it * it } = 25
      result shouldContain "11"  // applySum(4)(7) = 11
      result shouldContain "-25" // applyInverse { it * it }(5) = -25
    }

    test("functionLiteralsWithReceiver prints expected values") {
      val result = captureOutput { functionLiteralsWithReceiver() }
      result shouldContain "25" // square(5) = 25
      result shouldContain "50" // squared(5) { it * 2 } = 50
    }

    test("main runs without error") {
      captureOutput {
        Class.forName("org.athenian.lambdas._4_HofsKt")
          .getMethod("main")
          .invoke(null)
      }
    }
  },
)
