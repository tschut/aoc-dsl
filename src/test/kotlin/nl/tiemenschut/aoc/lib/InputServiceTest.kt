package nl.tiemenschut.aoc.lib

import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nl.tiemenschut.aoc.lib.dsl.AocService
import nl.tiemenschut.aoc.lib.dsl.InputService
import nl.tiemenschut.aoc.lib.dsl.day
import org.assertj.core.api.Assertions.assertThat
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

        assertThat(service.getInput(puzzle)).isEqualTo("I'm your puzzle input")
        verify { aocService wasNot called }
    }

    @Test
    fun `should save input in cache`() {
        val puzzle = 2020 day 10
        every { aocService.getInput(puzzle) }.returns("puzzle input")

        assertThat(service.getInput(puzzle)).isEqualTo("puzzle input")
        assertThat(Path("data" + puzzle.inputFile).readText()).isEqualTo("puzzle input")
    }
}