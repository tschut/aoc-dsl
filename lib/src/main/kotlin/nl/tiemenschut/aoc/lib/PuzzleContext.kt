package nl.tiemenschut.aoc.lib

class PuzzleContext(
    private val inputService: InputService,
    private val aocService: AocService,
    private val puzzle: Puzzle,
    private val level: Int
) {
    fun submit(answer: String) {
        println("Submitting answer: $answer")
        val response = aocService.submit(puzzle, answer)

        println("Response: $response")
    }
}
