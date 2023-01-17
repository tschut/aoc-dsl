package nl.tiemenschut.aoc.lib.dsl.parser

interface InputParser<T: Any> {
    fun parse(input: String): T
}