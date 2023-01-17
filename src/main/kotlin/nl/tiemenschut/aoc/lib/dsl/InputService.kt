package nl.tiemenschut.aoc.lib.dsl

import kotlin.io.path.*

class InputService(private val aocService: AocService) {
    private fun Puzzle.inputCacheFile() = Path(CACHE_ROOT + inputFile)

    fun getInput(puzzle: Puzzle): String =
        getInputFromCache(puzzle) ?: aocService.getInput(puzzle).also { put(puzzle, it) }

    private fun getInputFromCache(puzzle: Puzzle) = puzzle.inputCacheFile().takeIf { it.exists() }?.readText()

    private fun put(puzzle: Puzzle, input: String) = puzzle.inputCacheFile().run {
        parent.createDirectories()
        createFile()
        writeText(input)
    }
}