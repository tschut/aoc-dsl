package nl.tiemenschut.aoc.lib.dsl

enum class ResponseStatus {
    CORRECT, INCORRECT, UNKNOWN, DUPLICATE, TOO_RECENT, NOT_SUBMITTED
}

data class SubmitResponse(
    val status: ResponseStatus,
    val responseText: String
) {
    override fun toString(): String = "$status $responseText"
}