package nl.tiemenschut.aoc.lib.util.points

import kotlin.math.abs

infix fun Int.by(y: Int) = PointInt(this, y)

data class PointInt(override val x: Int, override val y: Int) : Point<Int> {
    override val DOWN: PointInt by lazy { PointInt(0, 1) }
    override val RIGHT: PointInt by lazy { PointInt(1, 0) }

    override fun plus(other: Point<Int>) = this.x + other.x by this.y + other.y

    override fun minus(other: Point<Int>) = this.x - other.x by this.y - other.y

    override fun manhattanDistance(other: Point<Int>) = abs(this.x - other.x) + abs(this.y - other.y)

    override fun toString() = "$x by $y"
}