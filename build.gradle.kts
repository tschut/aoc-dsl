val ktorVersion: String = "2.2.1"

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.20"
    `java-library`
    `maven-publish`
    id("pl.allegro.tech.build.axion-release") version "1.15.4"
    id("io.github.gradle-nexus.publish-plugin") version "1.2.0"
    signing
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(21))
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")


    testImplementation("io.kotest:kotest-framework-datatest:5.6.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
    testImplementation("com.marcinziolo:kotlin-wiremock:2.0.1")
    testImplementation("com.github.tomakehurst:wiremock-jre8:2.35.0")
    testImplementation("io.mockk:mockk:1.13.8")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

scmVersion {
    tag {
        prefix.set("")
    }
}

group = "io.github.tschut"
project.version = scmVersion.version

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "aoc-dsl"
            from(components["java"])
            pom {
                name.set("AoC DSL")
                description.set("A Kotlin DSL for adventofcode.")
                url.set("https://github.com/tschut/aoc-dsl")
                licenses {
                    license {
                        name.set("The Unlicense")
                        url.set("https://unlicense.org")
                    }
                }
                developers {
                    developer {
                        id.set("tiemenschut")
                        name.set("Tiemen Schut")
                        email.set("tschut@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/tschut/aoc-dsl.git")
                    developerConnection.set("scm:git:https://github.com/tschut/aoc-dsl.git")
                    url.set("https://github.com/tschut/aoc-dsl")
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}
