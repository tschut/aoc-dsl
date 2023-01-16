val ktorVersion: String = "2.2.1"

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.7.20"
    `java-library`
    id("net.researchgate.release") version "3.0.2"
    `maven-publish`
    signing
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
        create<MavenPublication>("mavenJava") {
            artifactId = "aoc-dsl"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
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
                        id.set("tschut")
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
    repositories {
        maven {
            name = "OSS"
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials(PasswordCredentials::class)
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}
