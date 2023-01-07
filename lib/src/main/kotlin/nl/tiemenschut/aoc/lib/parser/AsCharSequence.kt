package nl.tiemenschut.aoc.lib.parser

object AsCharSequence : InputParser<Sequence<Char>> {
    override fun parse(input: String): Sequence<Char> {
        return input.asSequence()
    }
}