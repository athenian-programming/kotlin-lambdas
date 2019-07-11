package org.athenian.lambdas

fun createString(block: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.block()
    return sb.toString()
}

fun main() {
    val s =
        createString {
            append(4)
            append("Hello World")
        }
    println(s)
}
