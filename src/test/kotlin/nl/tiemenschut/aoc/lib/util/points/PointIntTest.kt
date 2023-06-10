package nl.tiemenschut.aoc.lib.util.points

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PointIntTest : FunSpec({
    test("should construct PointInt with infix fun by") {
        (0 by 0) shouldBe PointInt(0, 0)
        (Int.MAX_VALUE by Int.MAX_VALUE) shouldBe PointInt(Int.MAX_VALUE, Int.MAX_VALUE)
    }

    test("add two points") {
        PointInt(-2598, 2839) + PointInt(2057, 23987) shouldBe PointInt(-541, 26826)
    }

    test("subtract two points") {
        PointInt(-2598, 2839) - PointInt(2057, 23987) shouldBe PointInt(-4655, -21148)
    }

    test("left") {
        PointInt(0, 0).left() shouldBe PointInt(-1, 0)
    }

    test("right") {
        PointInt(1234, 6789).right() shouldBe PointInt(1235, 6789)
    }

    test("up") {
        PointInt(1234, 6789).up() shouldBe PointInt(1234, 6788)
    }

    test("down") {
        PointInt(0, 0).down() shouldBe PointInt(0, 1)
    }

    data class CompareToTestData(val point: PointInt, val other: PointInt, val expected: Int)
    withData(
        CompareToTestData(PointInt(0, 0), PointInt(0, 0), 0),
        CompareToTestData(PointInt(0, 0), PointInt(0, -1), 1),
        CompareToTestData(PointInt(0, 0), PointInt(-1, 0), 1),
        CompareToTestData(PointInt(0, 0), PointInt(-1, -1), 1),
        CompareToTestData(PointInt(10, 0), PointInt(13, 0), -1),
        CompareToTestData(PointInt(10, 0), PointInt(13, 23), -1),
    ) { (point, other, expected) ->
        point.compareTo(other) shouldBe expected
    }

    test("manhattanDistance") {
        PointInt(10, 10).manhattanDistance(PointInt(-10, -10)) shouldBe 40
        PointInt(-10, -10).manhattanDistance(PointInt(10, 10)) shouldBe 40
    }
})