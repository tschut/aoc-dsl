package nl.tiemenschut.aoc.lib.util.points

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PointIntTest {
    @Test
    fun `should construct PointInt with infix fun by`() {
        (0 by 0) shouldBe PointInt(0, 0)
        (Int.MAX_VALUE by Int.MAX_VALUE) shouldBe PointInt(Int.MAX_VALUE, Int.MAX_VALUE)
    }

    @Test
    fun `add two points`() {
        PointInt(-2598, 2839) + PointInt(2057, 23987) shouldBe PointInt(-541, 26826)
    }

    @Test
    fun `subtract two points`() {
        PointInt(-2598, 2839) - PointInt(2057, 23987) shouldBe PointInt(-4655, -21148)
    }

    @Test
    fun left() {
        PointInt(0, 0).left() shouldBe PointInt(-1, 0)
    }

    @Test
    fun right() {
        PointInt(1234, 6789).right() shouldBe PointInt(1235, 6789)
    }

    @Test
    fun up() {
        PointInt(1234, 6789).up() shouldBe PointInt(1234, 6788)
    }

    @Test
    fun down() {
        PointInt(0, 0).down() shouldBe PointInt(0, 1)
    }

    @Test
    fun manhattanDistance() {
        PointInt(10, 10).manhattanDistance(PointInt(-10, -10)) shouldBe 40
        PointInt(-10, -10).manhattanDistance(PointInt(10, 10)) shouldBe 40
    }
}