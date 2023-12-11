package nl.tiemenschut.aoc.lib.util.grid

import nl.tiemenschut.aoc.lib.dsl.parser.InputParser

object CharGridParser : InputParser<Grid<Char>> {
    override fun parse(input: String): Grid<Char> {
        val lines = input.split("\n")

        val data = List(lines[0].length) { MutableList(lines.size) { ' ' } }.also { grid ->
            for (y in lines.indices) {
                for (x in lines[y].indices) {
                    grid[x][y] = lines[y][x]
                }
            }
        }

        return Grid(data)
    }
}
