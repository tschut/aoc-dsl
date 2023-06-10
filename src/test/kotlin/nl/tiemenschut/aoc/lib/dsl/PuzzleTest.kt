package nl.tiemenschut.aoc.lib.dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PuzzleTest {
    @Test
    fun `should return correct url for input`() {
        Puzzle(2015, 1).inputUrl shouldBe "/2015/day/1/input"
        Puzzle(2022, 25).inputUrl shouldBe "/2022/day/25/input"
    }

    @Test
    fun `should return correct url for answer`() {
        Puzzle(2015, 1).answerUrl shouldBe "/2015/day/1/answer"
        Puzzle(2022, 25).answerUrl shouldBe "/2022/day/25/answer"
    }

    @Test
    fun `should construct Puzzle correctly`() {
        2017 day 12 shouldBe Puzzle(2017, 12)
    }
}