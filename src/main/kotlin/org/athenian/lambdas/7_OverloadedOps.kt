package org.athenian.lambdas

object Console {
    operator fun invoke(block: (String) -> String): Nothing {
        while (true) {
            val l = readLine()
            if (!l.isNullOrBlank())
                println(block(l))
        }
    }
}

fun main() {
    println("Write your command!")
    Console {
        val parts = it.split(' ')
        when (parts[0]) {
            "go" -> if (parts.size > 1) "going ${parts[1]}" else "Missing go arg"
            "eat" -> if (parts.size > 1) "eating ${parts[1]}" else "Missing eateat arg"
            "quit" -> throw InterruptedException("Program has been terminated by user")
            else -> "I don't think so..."
        }
    }
}