package nl.tiemenschut.aoc.lib.util

fun <E> List<E>.infiniterator() = Infiniterator(this)

class Infiniterator<E>(val list: List<E>): Iterator<E> {
    var pos = 0

    override fun hasNext(): Boolean = true

    override fun next(): E = list[pos % list.size].also { pos = (pos + 1) % list.size }
}