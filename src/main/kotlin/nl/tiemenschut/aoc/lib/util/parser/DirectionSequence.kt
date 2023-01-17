package nl.tiemenschut.aoc.lib.util.parser

import nl.tiemenschut.aoc.lib.dsl.parser.InputParser
import nl.tiemenschut.aoc.lib.util.Direction

object DirectionSequence : InputParser<Sequence<Direction>> {
    override fun parse(input: String) = input.asSequence().map { Direction.fromChar(it) }
}