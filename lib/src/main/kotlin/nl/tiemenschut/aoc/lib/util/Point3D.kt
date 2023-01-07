package nl.tiemenschut.aoc.lib.util

infix fun Point.by(z: Long) = Point3D(this.x, this.y, z)
infix fun Point.by(z: Int) = Point3D(this.x, this.y, z.toLong())

data class Point3D(val x: Long, val y: Long, val z: Long) {
    fun moved(x1: Long, y1: Long, z1: Long) = (x + x1) by (y + y1) by (z + z1)

    fun neighbours() = setOf(
        x - 1 by y by z,
        x + 1 by y by z,
        x by y - 1 by z,
        x by y + 1 by z,
        x by y by z - 1,
        x by y by z + 1,
    )

    fun asList() = listOf(x, y, z)

    override fun toString(): String = "$x by $y by $z"
}