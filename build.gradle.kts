/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */


plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.6.0"

    // Apply the application plugin to add support for building a CLI application.
    application
}

apply(plugin = "org.jetbrains.kotlin.jvm")

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Align versions of amazon sdk components
    implementation(platform("com.amazonaws:aws-java-sdk-bom:1.11.772"))

    // Use the Kotlin JDK 8 standard library.
    implementation(kotlin("stdlib-jdk8"))

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Amazon AWS-S3 libraries
    implementation("com.amazonaws:aws-java-sdk-s3control")

    // Amazon DynamoDB
    implementation("com.amazonaws:aws-java-sdk-dynamodb")

    // GSon
    implementation("com.google.code.gson:gson:2.8.5")

    // kotlin-logging
    implementation("io.github.microutils:kotlin-logging:1.5.9")
    implementation("org.slf4j:slf4j-simple:1.7.26")

    //Clikt
    implementation("com.github.ajalt:clikt:2.6.0")

    //MockK
    testImplementation("io.mockk:mockk:1.9.3")

    //Junit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

application {
    // Define the main class for the application.
//    mainClassName = "dev.fanh.amazoncataloguploader.AppKt"
    mainClass.set("dev.fanh.amazoncataloguploader.AppKt")
}

