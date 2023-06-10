package nl.tiemenschut.aoc.lib.dsl

import io.kotest.matchers.shouldBe
import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nl.tiemenschut.aoc.lib.TestBase
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.readText
import kotlin.io.path.writeText

class InputServiceTest : TestBase() {
    private val aocService: AocService = mockk()
    private val service = InputService(aocService)

    @Test
    fun `should get input from cache if it exists`() {
        val puzzle = 2020 day 10
        Path("data" + puzzle.inputFile).run {
            parent.createDirectories()
            toFile().createNewFile()
            writeText("I'm your puzzle input")
        }

        service.getInput(puzzle) shouldBe "I'm your puzzle input"
        verify { aocService wasNot called }
    }

    @Test
    fun `should save input in cache`() {
        val puzzle = 2020 day 10
        every { aocService.getInput(puzzle) }.returns("puzzle input")

        service.getInput(puzzle) shouldBe "puzzle input"
        Path("data" + puzzle.inputFile).readText() shouldBe "puzzle input"
    }
}