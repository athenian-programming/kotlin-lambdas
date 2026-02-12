package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ScopeFunctionsTest : FunSpec(
    {

        val sft = ScopeFunctionTest()

        fun <T> suppressOutput(block: () -> T): T {
            val originalOut = System.out
            try {
                System.setOut(PrintStream(ByteArrayOutputStream()))
                return block()
            } finally {
                System.setOut(originalOut)
            }
        }

        test("letExample returns lambda result") {
            suppressOutput { sft.letExample() } shouldBe 42
        }

        test("alsoExample returns the original string") {
            suppressOutput { sft.alsoExample() } shouldBe "StringVal"
        }

        test("runExample returns lambda result") {
            suppressOutput { sft.runExample() } shouldBe 42
        }

        test("applyExample returns the original string") {
            suppressOutput { sft.applyExample() } shouldBe "StringVal"
        }

        test("withExample returns lambda result") {
            suppressOutput { sft.withExample() } shouldBe 42
        }

        test("main runs without error") {
            suppressOutput {
                Class.forName("org.athenian.lambdas._3_ScopeFunctionsKt")
                    .getMethod("main")
                    .invoke(null)
            }
        }
    },
)
