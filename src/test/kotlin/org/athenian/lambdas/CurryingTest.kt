package org.athenian.lambdas

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CurryingTest {

  @Test
  fun `Person data class properties`() {
    val person = Person("Alice", 30, 65.5)
    assertThat(person.name).isEqualTo("Alice")
    assertThat(person.age).isEqualTo(30)
    assertThat(person.weight).isEqualTo(65.5)
  }

  @Test
  fun `Person data class equality and hashCode`() {
    val p1 = Person("Bob", 25, 70.0)
    val p2 = Person("Bob", 25, 70.0)
    assertThat(p1).isEqualTo(p2)
    assertThat(p1.hashCode()).isEqualTo(p2.hashCode())
  }

  @Test
  fun `Person data class copy`() {
    val p1 = Person("Alice", 30, 65.5)
    val p2 = p1.copy(name = "Bob")
    assertThat(p2.name).isEqualTo("Bob")
    assertThat(p2.age).isEqualTo(30)
    assertThat(p2.weight).isEqualTo(65.5)
  }

  @Test
  fun `Person data class destructuring`() {
    val (name, age, weight) = Person("Alice", 30, 65.5)
    assertThat(name).isEqualTo("Alice")
    assertThat(age).isEqualTo(30)
    assertThat(weight).isEqualTo(65.5)
  }

  @Test
  fun `Person toString`() {
    val person = Person("Alice", 30, 65.5)
    assertThat(person.toString()).isEqualTo("Person(name=Alice, age=30, weight=65.5)")
  }

  @Test
  fun `personBuilder creates Person through currying`() {
    val person = personBuilder("Frank")(32)(78.5)
    assertThat(person).isEqualTo(Person("Frank", 32, 78.5))
  }

  @Test
  fun `personBuilder supports partial application`() {
    val frankBuilder = personBuilder("Frank")
    val frank32 = frankBuilder(32)
    val frank = frank32(78.5)
    assertThat(frank).isEqualTo(Person("Frank", 32, 78.5))
  }

  @Test
  fun `Currying frank is correctly built`() {
    assertThat(Currying.frank).isEqualTo(Person("Frank", 32, 78.5))
  }

  @Test
  fun `Currying people has correct names`() {
    val names = Currying.people.map { it.name }
    assertThat(names).containsExactly("Joe", "Mary", "Bob", "Alice")
  }

  @Test
  fun `Currying people has valid ages and weights`() {
    assertThat(Currying.people).hasSize(4)
    Currying.people.forEach { person ->
      assertThat(person.age).isBetween(0, 79)
      assertThat(person.weight).isBetween(0.0, 100.0)
    }
  }

  @Test
  fun `Currying main runs without error`() {
    val originalOut = System.out
    try {
      System.setOut(PrintStream(ByteArrayOutputStream()))
      Currying.main(arrayOf())
    } finally {
      System.setOut(originalOut)
    }
  }
}
