package nl.tiemenschut.aoc.lib.dsl.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasicParsersTest {
    @Test
    fun asCharSequenceTest() {
        val input = "inputstring"

        assertThat(AsCharSequence.parse(input).toList()).isEqualTo(input.toList())
    }

    @Test
    fun asListOfStringsTest() {
        val input = """
            first line
            second line
            third line
        """.trimIndent()

        assertThat(AsListOfStrings.parse(input)).isEqualTo(listOf("first line", "second line", "third line"))
    }
}