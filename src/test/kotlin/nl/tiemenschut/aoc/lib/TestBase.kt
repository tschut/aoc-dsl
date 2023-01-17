package nl.tiemenschut.aoc.lib

import org.junit.jupiter.api.BeforeEach
import kotlin.io.path.Path

open class TestBase {
    @BeforeEach
    fun removeDataDir() {
        Path("data").toFile().deleteRecursively()
    }
}
