package nl.tiemenschut.aoc.lib.util

import kotlin.math.abs

infix fun Long.by(y: Long) = Point(this, y)
infix fun Int.by(y: Int) = Point(this.toLong(), y.toLong())

data class Point(val x: Long, val y: Long) {
    fun left() = x - 1L by y
    fun right() = x + 1L by y
    fun up() = x by y - 1L
    fun down() = x by y + 1L

    fun moved(direction: Direction) = when (direction) {
        Direction.DOWN -> down()
        Direction.UP -> up()
        Direction.LEFT -> left()
        Direction.RIGHT -> right()
    }

    fun manhattenTo(o: Point) = abs(this.x - o.x) + abs(this.y - o.y)

    infix fun offsetBy(offset: Point) = x + offset.x by y + offset.y

    override fun toString() = "$x by $y"
}