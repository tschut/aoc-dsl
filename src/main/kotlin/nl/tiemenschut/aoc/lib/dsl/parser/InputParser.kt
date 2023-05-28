package nl.tiemenschut.aoc.lib.dsl.parser

fun interface InputParser<T: Any> {
    fun parse(input: String): T
}