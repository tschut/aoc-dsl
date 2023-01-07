kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "nl.tiemenschut"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
