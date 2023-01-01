package nl.tiemenschut.aoc.lib

const val ROOT_URL = "https://adventofcode.com"

fun aoc(lambda: AoC.() -> Unit) = AoC().apply(lambda)

class AoC {
    private lateinit var puzzle: Puzzle
    private val aocService = AocService(ROOT_URL)

    fun puzzle(puzzle: () -> Puzzle) {
        this.puzzle = puzzle()
    }

    private fun load(): String = aocService.getInput(puzzle)

    fun submit(answer: String) {
        println("Submitting answer: $answer")
        val response = aocService.submit(puzzle, answer)

        println("Response: $response")
    }

    fun part1(solver: (String) -> Unit) {
        apply { solver(load()) }
    }
}