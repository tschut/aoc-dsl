package nl.tiemenschut.aoc.lib.dsl.parser

object NoopParser : InputParser<String> {
    override fun parse(input: String) = input
}

object AsCharSequence : InputParser<Sequence<Char>> {
    override fun parse(input: String): Sequence<Char> {
        return input.asSequence()
    }
}

