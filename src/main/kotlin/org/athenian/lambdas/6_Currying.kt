package org.athenian.lambdas._7_kotlin_lambdas

import kotlin.random.Random.Default.nextDouble
import kotlin.random.Random.Default.nextInt

// See https://en.wikipedia.org/wiki/Currying

data class Person(val name: String, val age: Int, val weight: Double)

val personBuilder: (String) -> (Int) -> (Double) -> Person =
    { name ->
        { age ->
            { weight ->
                Person(name, age, weight)
            }
        }
    }

object Currying {

    val frank = personBuilder("Frank")(32)(78.5)

    val names = listOf("Joe", "Mary", "Bob", "Alice")
    val people: List<Person> =
        names.map { personBuilder(it) }     //assign the name
            .map { it(nextInt(80)) }        //a random age
            .map { it(nextDouble(100.0)) }  //a random weight

    @JvmStatic
    fun main(args: Array<String>) {
        println(people)
    }
}