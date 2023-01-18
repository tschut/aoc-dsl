package nl.tiemenschut.aoc.lib.dsl

class SessionInvalidException(private val msg: String) : Exception() {
    override fun toString() = buildString {
        appendLine(msg)
        appendLine("Looks like your session id is missing or expired, please login to aoc and update the .session file with the current session id.")
        append(super.toString())
    }
}
