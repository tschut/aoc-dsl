package nl.tiemenschut.aoc.lib

const val ROOT_URL = "https://adventofcode.com"
const val CACHE_ROOT = "data"

fun aoc(lambda: AoC.() -> Unit) = AoC().apply(lambda)

class AoC {
    private lateinit var puzzle: Puzzle
    private val aocService = AocService(ROOT_URL)
    private val inputService = InputService(aocService)
    private val submitService = SubmitService(aocService)

    fun puzzle(puzzle: () -> Puzzle) {
        this.puzzle = puzzle()
    }

    private fun input(): String = inputService.getInput(puzzle)

    fun part1(solver: PuzzleContext.(String) -> Unit) {
        runSolver(0, solver)
    }

    fun part2(solver: PuzzleContext.(String) -> Unit) {
        runSolver(1, solver)
    }

    private fun runSolver(level: Int, solver: PuzzleContext.(String) -> Unit) {
        val input = input()

        PuzzleContext(inputService, submitService, puzzle, level).apply { solver(input) }
    }
}