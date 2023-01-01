package nl.tiemenschut.aoc.lib

sealed class SubmitResponse(private val responseText: String) {
    class Correct(responseText: String) : SubmitResponse(responseText)
    class Incorrect(responseText: String) : SubmitResponse(responseText)
    class Unknown(responseText: String) : SubmitResponse(responseText)

    override fun toString(): String = "${this::class}: $responseText"
}