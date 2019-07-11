package org.athenian.lambdas

import org.athenian.lambdas.ReceiptTextFunc.receiptText

// From https://proandroiddev.com/kotlin-pearls-3-its-an-object-it-s-a-function-it-s-an-invokable-bc4bfed2e63f

class ReceiptText(val template: String) : (Int) -> String {
    override fun invoke(amount: Int) = template.replace("%", amount.toString())

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val receiptText = ReceiptText("Bill amount: $%")
            println(receiptText(5))
            // or
            println(ReceiptText("Bill amount: $%")(5))
        }
    }
}

object ReceiptTextFunc {
    fun receiptText(template: String): (Int) -> String = { amount ->
        template.replace("%", amount.toString())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val hof = receiptText("Bill amount: $%")
        println(hof(5))
        //or
        println(receiptText("Bill amount: $%")(5))
    }
}

// An object with invoke operators
object ReceiptTextObj {
    operator fun invoke(amount: Int): String =
        receiptText("Bill amount: $%")(amount)

    operator fun invoke(template: String, amount: Int): String =
        receiptText(template)(amount)

    @JvmStatic
    fun main(args: Array<String>) {
        println(ReceiptTextObj(5))
        println(ReceiptTextObj("Bill amount: $%", 5))
    }
}

object ReceiptTextCombo {
    @JvmStatic
    fun main(args: Array<String>) {
        val functions =
            mutableListOf<(Int) -> String>()
                .apply {
                    add(ReceiptText("Thank you for $%!"))
                    add(receiptText("TA %"))
                    add(ReceiptTextObj::invoke)
                }
        val receipts = functions.mapIndexed { i, func -> func(i + 100) }
        println(receipts)
    }
}