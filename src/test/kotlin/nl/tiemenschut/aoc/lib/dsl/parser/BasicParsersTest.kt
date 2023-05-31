package nl.tiemenschut.aoc.lib.dsl.parser

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BasicParsersTest {
    @Test
    fun asCharSequenceTest() {
        val input = "inputstring"

        AsCharSequence.parse(input).toList() shouldBe input.toList()
    }

    @Test
    fun asListOfStringsTest() {
        val input = """
            first line
            second line
            third line
        """.trimIndent()

        AsListOfStrings.parse(input) shouldBe listOf("first line", "second line", "third line")
    }
}