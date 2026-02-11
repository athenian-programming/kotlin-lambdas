# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Kotlin educational repository demonstrating lambda expressions and functional programming concepts. Contains numbered
example files (0-7) in `src/main/kotlin/org/athenian/lambdas/`, each with its own `main()` function for direct
execution.

## Build Commands

- **Build project**: `./gradlew build -xtest` or `make build`
- **Clean**: `./gradlew clean` or `make clean`
- **Check for dependency updates**: `./gradlew dependencyUpdates` or `make versioncheck`
- **Upgrade Gradle wrapper**: `make upgrade-wrapper`

No test files exist currently; tests are excluded from the default build (`-xtest`).

## Architecture

- Kotlin 2.2.0 with JVM toolchain 17
- Gradle with version catalog (`gradle/libs.versions.toml`) for dependency management
- Dependencies: Logback (logging), AssertJ (testing)
- Examples progress from basic lambda syntax (0) through list processing (1), higher-order functions (2, 4), scope
  functions (3), invoke operator (5), currying (6), and operator overloading (7)
