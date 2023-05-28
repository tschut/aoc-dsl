package nl.tiemenschut.aoc.lib.dsl.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasicParsersTest {
    @Test
    fun asCharSequenceTest() {
        val input = "inputstring"

        assertThat(AsCharSequence.parse(input).toList()).isEqualTo(input.toList())
    }
}