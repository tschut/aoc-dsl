package nl.tiemenschut.aoc.lib.util.points

import kotlin.math.abs

infix fun Long.by(y: Long) = PointLong(this, y)

data class PointLong(override val x: Long, override val y: Long) : Point<Long> {
    override val DOWN: PointLong by lazy { PointLong(0, 1) }
    override val RIGHT: PointLong by lazy { PointLong(1, 0) }

    override fun compareTo(other: Point<Long>): Int {
        TODO("Not yet implemented")
    }

    override fun plus(other: Point<Long>) = this.x + other.x by this.y + other.y

    override fun minus(other: Point<Long>) = this.x - other.x by this.y - other.y

    override fun manhattanDistance(other: Point<Long>) = abs(this.x - other.x) + abs(this.y - other.y)

    override fun toString() = "$x by $y"
}