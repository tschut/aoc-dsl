package nl.tiemenschut.aoc.y2015

import nl.tiemenschut.aoc.lib.dsl.aoc
import nl.tiemenschut.aoc.lib.dsl.day
import nl.tiemenschut.aoc.lib.util.by
import nl.tiemenschut.aoc.lib.util.parser.DirectionSequence

fun main() {
    aoc(DirectionSequence) {
        puzzle { 2015 day 3 }

        part1{ input ->
            val positions = mutableSetOf(0 by 0)
            var current = 0 by 0
            input.forEach { direction ->
                current = current.moved(direction)
                positions.add(current)
            }

            return@part1 positions.size
        }

        part2 { input ->
            val houses = mutableSetOf(0 by 0)
            var santa = 0 by 0
            var roboSanta = 0 by 0
            input.forEachIndexed { index, direction ->
                if (index % 2 == 0) {
                    santa = santa.moved(direction).also { houses.add(it) }
                } else {
                    roboSanta = roboSanta.moved(direction).also { houses.add(it) }
                }
            }

            return@part2 houses.size
        }
    }
}