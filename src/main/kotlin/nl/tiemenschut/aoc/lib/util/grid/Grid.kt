package nl.tiemenschut.aoc.lib.util.grid

import nl.tiemenschut.aoc.lib.util.points.Point
import nl.tiemenschut.aoc.lib.util.points.by

class Grid<T>(val data: List<MutableList<T>>) {
    fun width() = data.size
    fun height() = data[0].size

    fun allIndexOff(value: T) = (0..<width()).flatMap { x ->
        (0..<height()).mapNotNull { y ->
            if (get(x by y) == value) x by y else null
        }
    }

    operator fun get(p: Point<Int>) = data[p.x][p.y]

    operator fun set(p: Point<Int>, value: T) {
        data[p.x][p.y] = value
    }

    override fun toString() = buildString {
        for (y in 0..<height()) {
            for (x in 0..<width()) {
                append(get(x by y))
            }
            appendLine()
        }
    }


}