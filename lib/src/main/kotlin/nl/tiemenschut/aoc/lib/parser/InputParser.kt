package nl.tiemenschut.aoc.lib.parser

interface InputParser<T: Any> {
    fun parse(input: String): T
}