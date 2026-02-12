package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class SimpleExampleTest : FunSpec(
  {

    test("constant1 returns 4") {
      constant1() shouldBe 4
      constant1.invoke() shouldBe 4
    }

    test("constant2 returns 4") {
      constant2() shouldBe 4
      constant2.invoke() shouldBe 4
    }

    test("adder1 adds two integers") {
      adder1(4, 5) shouldBe 9
      adder1(0, 0) shouldBe 0
      adder1(-1, 1) shouldBe 0
    }

    test("adder2 adds two integers") {
      adder2(4, 5) shouldBe 9
      adder2.invoke(4, 5) shouldBe 9
    }

    test("sum adds two integers") {
      sum(5, 6) shouldBe 11
      sum(0, 0) shouldBe 0
      sum(-3, 3) shouldBe 0
    }

    test("twoIntFunc applies block to two integers") {
      twoIntFunc(5, 6) { x, y -> x * y } shouldBe 30
      twoIntFunc(5, 6) { x, y -> x + y } shouldBe 11
      twoIntFunc(5, 6) { x, y -> sum(x, y) } shouldBe 11
      twoIntFunc(7, 8, ::sum) shouldBe 15
    }

    test("main runs without error") {
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
  },
)
