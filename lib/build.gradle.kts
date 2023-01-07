val ktorVersion: String = "2.2.1"

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.7.20"
    `java-library`
    `maven-publish`
    id("net.researchgate.release") version "3.0.2"
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("com.marcinziolo:kotlin-wiremock:2.0.1")
    testImplementation("com.github.tomakehurst:wiremock-jre8:2.35.0")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>(name = "aoc-dsl") {
            from(components["java"])
            groupId = "com.github.tschut"
            artifactId = "aoc-dsl"
        }
    }

    repositories {
        mavenCentral()
    }
}
