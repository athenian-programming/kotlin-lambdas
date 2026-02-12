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

    testImplementation(libs.assertj.core)
    testImplementation(kotlin("test"))
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