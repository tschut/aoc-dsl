package nl.tiemenschut.aoc.lib.util.points

operator fun PointInt.rangeTo(other: PointInt): PointIntRange = PointIntRange(this, other)

class PointIntRange(
    start: PointInt,
    endInclusive: PointInt,
) : PointIntProgression(start, endInclusive), ClosedRange<PointInt> {
    override val start: PointInt get() = first
    override val endInclusive get() = last

    override fun equals(other: Any?): Boolean =
        other is PointIntRange && (isEmpty() && other.isEmpty() || first == other.first && last == other.last)

    override fun hashCode(): Int = if (isEmpty()) -1 else (31 * first.hashCode() + last.hashCode())
}

open class PointIntProgression(
    start: PointInt,
    endInclusive: PointInt,
) : Iterable<Point<Int>> {
    val first = start
    val last = endInclusive

    override fun iterator(): Iterator<Point<Int>> = PointIntProgressionIterator(first, last)
}

class PointIntProgressionIterator(private val first: Point<Int>, private val last: Point<Int>) : Iterator<Point<Int>> {
    private var hasNext = first.x <= last.x && first.y <= last.y
    private var next = if (hasNext) first else last

    override fun hasNext() = hasNext

    override fun next(): Point<Int> {
        val value = next
        if (value == last) {
            if (!hasNext) throw NoSuchElementException()
            hasNext = false
        } else {
            next = if (next.x < last.x) next.right() else {
                PointInt(first.x, next.y) + next.DOWN
            }
        }
        return value
    }

}
