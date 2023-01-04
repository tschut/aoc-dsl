package nl.tiemenschut.aoc.lib

enum class ResponseStatus {
    CORRECT, INCORRECT, UNKNOWN, DUPLICATE
}

data class SubmitResponse(
    val status: ResponseStatus,
    val responseText: String
) {
    override fun toString(): String = "$status $responseText"
}