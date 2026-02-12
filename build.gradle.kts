plugins {
    java
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.versions)
}

group = "org.athenian"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.logback.core)
    implementation(libs.logback.classic)
    implementation(libs.slf4j.jul)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

kotlin {
    jvmToolchain(17)
}

fun isNonStable(version: String): Boolean {
    // val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val betaKeyword = listOf("-RC", "-BETA", "-M", "-ALPHA").any { version.uppercase().contains(it) }
    // val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = !betaKeyword //(stableKeyword || regex.matches(version)) && !betaKeyword
    return !isStable
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}
