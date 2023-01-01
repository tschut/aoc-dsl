package nl.tiemenschut.aoc.lib

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PuzzleTest {
    @Test
    fun `should return correct url for input`() {
        assertThat(Puzzle(2015, 1).inputUrl).isEqualTo("/2015/day/1/input")
        assertThat(Puzzle(2022, 25).inputUrl).isEqualTo("/2022/day/25/input")
    }

    @Test
    fun `should return correct url for answer`() {
        assertThat(Puzzle(2015, 1).answerUrl).isEqualTo("/2015/day/1/answer")
        assertThat(Puzzle(2022, 25).answerUrl).isEqualTo("/2022/day/25/answer")
    }

    @Test
    fun `should construct Puzzle correctly`() {
        assertThat(2017 day 12).isEqualTo(Puzzle(2017, 12))
    }
}