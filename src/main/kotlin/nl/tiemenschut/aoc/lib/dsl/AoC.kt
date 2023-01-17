package nl.tiemenschut.aoc.lib.dsl

import nl.tiemenschut.aoc.lib.dsl.parser.InputParser
import nl.tiemenschut.aoc.lib.dsl.parser.NoopParser
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

const val ROOT_URL = "https://adventofcode.com"
const val CACHE_ROOT = "data"

fun aoc(lambda: AoC<String>.() -> Unit) = AoC(NoopParser).apply(lambda)
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

    fun part1(submit: Boolean = true, solver: PuzzleContext.(T) -> Any) {
        runSolver(submit, 1, solver)
    }

    fun part2(submit: Boolean = true, solver: PuzzleContext.(T) -> Any) {
        runSolver(submit, 2, solver)
    }

    @OptIn(ExperimentalTime::class)
    private fun runSolver(submit: Boolean, level: Int, solver: PuzzleContext.(T) -> Any) {
        val input = input()

        var answer: String?
        val context = PuzzleContext(submitService, puzzle, level)

        val time = measureTime {
            answer = context.run { solver(input) }.toString()
        }

        println("Answer for $puzzle part $level: $answer, calculated in $time.")

        if (submit) context.submit(answer!!)
    }
}