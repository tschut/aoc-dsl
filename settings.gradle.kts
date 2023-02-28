rootProject.name = "aoc"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

pluginManagement {
    val kotlinVersion: String by settings

    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
    }
}
