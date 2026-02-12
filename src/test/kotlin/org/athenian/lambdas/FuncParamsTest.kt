package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FuncParamsTest : FunSpec(
    {

        test("createString builds string using StringBuilder receiver") {
            val result = createString {
                append("Hello")
                append(" ")
                append("World")
            }
            result shouldBe "Hello World"
        }

        test("createString with numbers and text") {
            val result = createString {
                append(4)
                append("Hello World")
            }
            result shouldBe "4Hello World"
        }

        test("createString with empty block returns empty string") {
            val result = createString {}
            result.shouldBeEmpty()
        }

        test("main runs without error") {
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
    },
)
