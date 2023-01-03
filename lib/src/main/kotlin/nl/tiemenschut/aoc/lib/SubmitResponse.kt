package nl.tiemenschut.aoc.lib

sealed class SubmitResponse(private val responseText: String) {
    class Correct(responseText: String) : SubmitResponse(responseText) {
        override val name = "Correct 🥳"
    }

    class Incorrect(responseText: String) : SubmitResponse(responseText) {
        override val name = "Incorrect!"
    }

    class Unknown(responseText: String) : SubmitResponse(responseText) {
        override val name = "Unknown response!"
    }

    abstract val name: String

    override fun toString(): String = "$name $responseText"
}