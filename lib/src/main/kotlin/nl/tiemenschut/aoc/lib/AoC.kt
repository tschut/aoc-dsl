package nl.tiemenschut.aoc.lib

import nl.tiemenschut.aoc.lib.parser.InputParser
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

const val ROOT_URL = "https://adventofcode.com"
const val CACHE_ROOT = "data"

fun <T: Any> aoc(parser: InputParser<T>, lambda: AoC<T>.() -> Unit) = AoC(parser).apply(lambda)

class AoC<T: Any>(private val parser: InputParser<T>) {
    private lateinit var puzzle: Puzzle

    private val aocService = AocService(ROOT_URL)
    private val inputService = InputService(aocService)
    private val submitService = SubmitService(aocService)

    fun puzzle(puzzle: () -> Puzzle) {
        this.puzzle = puzzle()
    }

    private fun input(): T = inputService.getInput(puzzle).let { parser.parse(it) }

    fun part1(solver: PuzzleContext.(T) -> String) {
        runSolver(1, solver)
    }

    fun part2(solver: PuzzleContext.(T) -> String) {
        runSolver(2, solver)
    }

    @OptIn(ExperimentalTime::class)
    private fun runSolver(level: Int, solver: PuzzleContext.(T) -> String) {
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