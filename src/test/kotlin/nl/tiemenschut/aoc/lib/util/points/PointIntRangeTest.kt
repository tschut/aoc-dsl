package nl.tiemenschut.aoc.lib.util.points

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PointIntRangeTest : FunSpec({
    test("should create range") {
        (0 by 0)..(2 by 2) shouldBe PointIntRange(0 by 0, 2 by 2)
    }

    test("should iterate right first then down") {
        val iterator = PointIntProgressionIterator(0 by 0, 3 by 2)

        iterator.hasNext() shouldBe true
        iterator.next() shouldBe (0 by 0)
        iterator.next() shouldBe (1 by 0)
        iterator.next() shouldBe (2 by 0)
        iterator.next() shouldBe (3 by 0)
        iterator.next() shouldBe (0 by 1)
        iterator.next() shouldBe (1 by 1)
        iterator.next() shouldBe (2 by 1)
        iterator.next() shouldBe (3 by 1)
        iterator.next() shouldBe (0 by 2)
        iterator.next() shouldBe (1 by 2)
        iterator.next() shouldBe (2 by 2)
        iterator.next() shouldBe (3 by 2)
        iterator.hasNext() shouldBe false
        shouldThrow<NoSuchElementException> { iterator.next() }
    }
})