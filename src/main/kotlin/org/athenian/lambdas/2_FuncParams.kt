package org.athenian.lambdas._7_kotlin_lambdas

fun createString(block: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.block()
    return sb.toString()
}

fun main(args: Array<String>) {
    val s =
        createString {
            append(4)
            append("Hello World")
        }
    println(s)
}
