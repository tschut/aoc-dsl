package nl.tiemenschut.aoc.lib.util

enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    fun rotateLeft(): Direction = when(this) {
        UP -> LEFT
        DOWN -> RIGHT
        LEFT -> DOWN
        RIGHT -> UP
    }

    fun rotateRight(): Direction = when(this) {
        UP -> RIGHT
        DOWN -> LEFT
        LEFT -> UP
        RIGHT -> DOWN
    }

    companion object {
        fun fromChar(c: Char) = when (c) {
            '^' -> UP
            'v' -> DOWN
            '<' -> LEFT
            '>' -> RIGHT
            else -> throw UnsupportedOperationException()
        }
    }
}