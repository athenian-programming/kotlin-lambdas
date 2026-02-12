package org.athenian.lambdas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.doubles.shouldBeBetween
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CurryingTest : FunSpec(
  {

    test("Person data class properties") {
      val person = Person("Alice", 30, 65.5)
      person.name shouldBe "Alice"
      person.age shouldBe 30
      person.weight shouldBe 65.5
    }

    test("Person data class equality and hashCode") {
      val p1 = Person("Bob", 25, 70.0)
      val p2 = Person("Bob", 25, 70.0)
      p1 shouldBe p2
      p1.hashCode() shouldBe p2.hashCode()
    }

    test("Person data class copy") {
      val p1 = Person("Alice", 30, 65.5)
      val p2 = p1.copy(name = "Bob")
      p2.name shouldBe "Bob"
      p2.age shouldBe 30
      p2.weight shouldBe 65.5
    }

    test("Person data class destructuring") {
      val (name, age, weight) = Person("Alice", 30, 65.5)
      name shouldBe "Alice"
      age shouldBe 30
      weight shouldBe 65.5
    }

    test("Person toString") {
      val person = Person("Alice", 30, 65.5)
      person.toString() shouldBe "Person(name=Alice, age=30, weight=65.5)"
    }

    test("personBuilder creates Person through currying") {
      val person = personBuilder("Frank")(32)(78.5)
      person shouldBe Person("Frank", 32, 78.5)
    }

    test("personBuilder supports partial application") {
      val frankBuilder = personBuilder("Frank")
      val frank32 = frankBuilder(32)
      val frank = frank32(78.5)
      frank shouldBe Person("Frank", 32, 78.5)
    }

    test("Currying frank is correctly built") {
      Currying.frank shouldBe Person("Frank", 32, 78.5)
    }

    test("Currying people has correct names") {
      val names = Currying.people.map { it.name }
      names shouldContainExactly listOf("Joe", "Mary", "Bob", "Alice")
    }

    test("Currying people has valid ages and weights") {
      Currying.people shouldHaveSize 4
      Currying.people.forEach { person ->
        person.age.shouldBeInRange(0..79)
        person.weight.shouldBeBetween(0.0, 100.0, 0.0)
      }
    }

    test("Currying main runs without error") {
      val originalOut = System.out
      try {
        System.setOut(PrintStream(ByteArrayOutputStream()))
        Currying.main(arrayOf())
      } finally {
        System.setOut(originalOut)
      }
    }
  },
)
