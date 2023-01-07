package nl.tiemenschut.aoc.lib

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

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

    fun part1(solver: PuzzleContext.(String) -> String) {
        runSolver(1, solver)
    }

    fun part2(solver: PuzzleContext.(String) -> String) {
        runSolver(2, solver)
    }

    @OptIn(ExperimentalTime::class)
    private fun runSolver(level: Int, solver: PuzzleContext.(String) -> String) {
        val input = input()

        var answer: String?
        val context = PuzzleContext(inputService, submitService, puzzle, level)

        val time = measureTime {
            answer = context.run { solver(input) }
        }

        println("Answer for $puzzle part $level: $answer, calculated in $time.")

        context.submit(answer!!)
    }
}