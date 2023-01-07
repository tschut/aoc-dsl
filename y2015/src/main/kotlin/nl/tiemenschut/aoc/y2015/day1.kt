package nl.tiemenschut.aoc.y2015

import nl.tiemenschut.aoc.lib.aoc
import nl.tiemenschut.aoc.lib.day

fun main() {
    aoc {
        puzzle { 2015 day 1 }

        part1 { input ->
            var floor = 0
            for (c in input) when (c) {
                '(' -> floor++
                else -> floor--
            }

            return@part1 "$floor"
        }

        part2 { input ->
            var floor = 0
            input.forEachIndexed { index, c ->
                when (c) {
                    '(' -> floor++
                    else -> floor--
                }
                if (floor < 0) {
                    return@part2 "${index + 1}"
                }
            }
            throw Exception()
        }
    }
}


