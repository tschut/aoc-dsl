package nl.tiemenschut.aoc.y2015

import nl.tiemenschut.aoc.lib.dsl.aoc
import nl.tiemenschut.aoc.lib.dsl.day
import nl.tiemenschut.aoc.lib.dsl.parser.InputParser
import nl.tiemenschut.aoc.lib.util.Point3D
import nl.tiemenschut.aoc.lib.util.by

object BoxParser : InputParser<List<Point3D>> {
    override fun parse(input: String): List<Point3D> = input.lines()
        .map { it.split("x") }
        .map { it[0].toInt() by it[1].toInt() by it[2].toInt() }
}

fun main() {
    aoc(BoxParser) {
        puzzle { 2015 day 2 }

        part1 { input: List<Point3D> ->
            fun Point3D.wrapping(): Long {
                val areas = listOf(x * y, x * z, y * z)
                return areas.min() + areas.sumOf { it * 2 }
            }

            return@part1 input.sumOf { it.wrapping() }
        }

        part2 { input: List<Point3D> ->
            fun Point3D.ribbon(): Long {
                return asList().sorted().take(2).sumOf { it * 2 } + x * y * z
            }

            return@part2 input.sumOf { it.ribbon() }
        }
    }
}