package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InvokeTest : FunSpec(
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

    test("ReceiptText invoke replaces placeholder with amount") {
      val receiptText = ReceiptText("Bill amount: $%")
      receiptText(5) shouldBe "Bill amount: $5"
      receiptText.invoke(10) shouldBe "Bill amount: $10"
    }

    test("ReceiptText template property is accessible") {
      val receiptText = ReceiptText("Template: %")
      receiptText.template shouldBe "Template: %"
    }

    test("ReceiptText companion main runs without error") {
      captureOutput { ReceiptText.main(arrayOf()) }
    }

    test("ReceiptTextFunc receiptText returns function that replaces placeholder") {
      val hof = ReceiptTextFunc.receiptText("Bill amount: $%")
      hof(5) shouldBe "Bill amount: $5"
      ReceiptTextFunc.receiptText("Amount: %")(100) shouldBe "Amount: 100"
    }

    test("ReceiptTextFunc main runs without error") {
      captureOutput { ReceiptTextFunc.main(arrayOf()) }
    }

    test("ReceiptTextObj invoke with amount only") {
      ReceiptTextObj(5) shouldBe "Bill amount: $5"
    }

    test("ReceiptTextObj invoke with template and amount") {
      ReceiptTextObj("Custom: $%", 10) shouldBe "Custom: $10"
    }

    test("ReceiptTextObj main runs without error") {
      captureOutput { ReceiptTextObj.main(arrayOf()) }
    }

    test("ReceiptTextCombo main produces correct receipts") {
      val result = captureOutput { ReceiptTextCombo.main(arrayOf()) }
      result shouldContain "Thank you for $100!"
      result shouldContain "TA 101"
      result shouldContain "Bill amount: $102"
    }
  },
)
