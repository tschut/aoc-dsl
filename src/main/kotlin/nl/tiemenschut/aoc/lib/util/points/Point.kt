package nl.tiemenschut.aoc.lib.util.points

import nl.tiemenschut.aoc.lib.util.Direction

interface Point<T> : Comparable<Point<T>> {
    val x: T
    val y: T

    val DOWN: Point<T>

    val RIGHT: Point<T>

    operator fun plus(other: Point<T>): Point<T>

    operator fun minus(other: Point<T>): Point<T>

    fun left(): Point<T> = this - RIGHT
    fun right(): Point<T> = this + RIGHT
    fun up(): Point<T> = this - DOWN
    fun down(): Point<T> = this + DOWN

    fun moved(direction: Direction) = when (direction) {
        Direction.DOWN -> down()
        Direction.UP -> up()
        Direction.LEFT -> left()
        Direction.RIGHT -> right()
    }

    fun manhattanDistance(other: Point<T>): T
}