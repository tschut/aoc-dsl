package nl.tiemenschut.aoc.lib.dsl.parser

val NoopParser = InputParser { it }

val AsCharSequence = InputParser { it.asSequence() }

val AsListOfStrings = InputParser { it.split("\n") }

