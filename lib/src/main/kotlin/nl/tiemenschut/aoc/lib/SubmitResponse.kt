package nl.tiemenschut.aoc.lib

enum class ResponseStatus {
    CORRECT, INCORRECT, UNKNOWN, DUPLICATE, TOO_RECENT
}

data class SubmitResponse(
    val status: ResponseStatus,
    val responseText: String
) {
    override fun toString(): String = "$status $responseText"
}