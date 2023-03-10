package nl.tiemenschut.aoc.lib.dsl

class PuzzleContext(
    private val submitService: SubmitService,
    private val puzzle: Puzzle,
    private val level: Int
) {
    fun submit(answer: String) {
        println("Submitting answer: $answer")
        val response = submitService.submit(puzzle, level, answer)

        println("Response: $response")
    }
}
