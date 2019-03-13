# Kotlin Lambdas

This repo contains code snippets useful for understanding Kotlin lambdas.

## Tutorials
* [Kotlin Lambda Expressions](http://www.baeldung.com/kotlin-lambda-expressions)
* [Lambdas with a Context](https://proandroiddev.com/kotlin-pearls-lambdas-with-a-context-58f26ab2eb1d)

## Examples
* [Kotlin Lambdas](src/main/kotlin/org/athenian/lambdas)

## Kotlin Lambda Example
```kotlin
students
    .filter { it.passing && it.averageGrade > 3.5 }     // Only passing students with high GPAs
    .sortedByDescending { it.averageGrade }             // Starting from ones with best grades
    .take(10)                                           // Take top 10
    .sortedWith(compareBy({ it.surname }, { it.name })) // Sort by surname and then name
```

