package nl.tiemenschut.aoc.y2015

import nl.tiemenschut.aoc.lib.aoc
import nl.tiemenschut.aoc.lib.day

fun main() {
    aoc {
        puzzle { 2015 day 1 }

        part1 { input ->
            var floor = 0
            for (c in input) when (c) {
                '(' -> floor ++
                else -> floor --
            }

            submit("$floor")
        }

        // next step: create SubmitService that does caching for submissions
//        part2 { input ->
//            var floor = 0
//            for (c in input) when (c) {
//                '(' -> floor ++
//                else -> floor --
//            }
//
//            submit("$floor")
//        }
    }
}


