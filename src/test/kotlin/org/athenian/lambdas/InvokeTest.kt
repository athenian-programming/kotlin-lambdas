package org.athenian.lambdas

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InvokeTest {

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
  fun `ReceiptText invoke replaces placeholder with amount`() {
    val receiptText = ReceiptText("Bill amount: $%")
    assertThat(receiptText(5)).isEqualTo("Bill amount: $5")
    assertThat(receiptText.invoke(10)).isEqualTo("Bill amount: $10")
  }

  @Test
  fun `ReceiptText template property is accessible`() {
    val receiptText = ReceiptText("Template: %")
    assertThat(receiptText.template).isEqualTo("Template: %")
  }

  @Test
  fun `ReceiptText companion main runs without error`() {
    captureOutput { ReceiptText.main(arrayOf()) }
  }

  @Test
  fun `ReceiptTextFunc receiptText returns function that replaces placeholder`() {
    val hof = ReceiptTextFunc.receiptText("Bill amount: $%")
    assertThat(hof(5)).isEqualTo("Bill amount: $5")
    assertThat(ReceiptTextFunc.receiptText("Amount: %")(100)).isEqualTo("Amount: 100")
  }

  @Test
  fun `ReceiptTextFunc main runs without error`() {
    captureOutput { ReceiptTextFunc.main(arrayOf()) }
  }

  @Test
  fun `ReceiptTextObj invoke with amount only`() {
    assertThat(ReceiptTextObj(5)).isEqualTo("Bill amount: $5")
  }

  @Test
  fun `ReceiptTextObj invoke with template and amount`() {
    assertThat(ReceiptTextObj("Custom: $%", 10)).isEqualTo("Custom: $10")
  }

  @Test
  fun `ReceiptTextObj main runs without error`() {
    captureOutput { ReceiptTextObj.main(arrayOf()) }
  }

  @Test
  fun `ReceiptTextCombo main produces correct receipts`() {
    val result = captureOutput { ReceiptTextCombo.main(arrayOf()) }
    assertThat(result).contains("Thank you for $100!")
    assertThat(result).contains("TA 101")
    assertThat(result).contains("Bill amount: $102")
  }
}
