package nl.tiemenschut.aoc.lib.util.points

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PointLongTest {
    @Test
    fun `should construct PointLong with infix fun by`() {
        (0L by 0L) shouldBe PointLong(0, 0)
        (Long.MAX_VALUE by Long.MAX_VALUE) shouldBe PointLong(Long.MAX_VALUE, Long.MAX_VALUE)
    }

    @Test
    fun `add two points`() {
        PointLong(-2598, 2839) + PointLong(2057, 23987) shouldBe PointLong(-541, 26826)
    }

    @Test
    fun `subtract two points`() {
        PointLong(-2598, 2839) - PointLong(2057, 23987) shouldBe PointLong(-4655, -21148)
    }

    @Test
    fun left() {
        PointLong(0, 0).left() shouldBe PointLong(-1, 0)
    }

    @Test
    fun right() {
        PointLong(1234, 6789).right() shouldBe PointLong(1235, 6789)
    }

    @Test
    fun up() {
        PointLong(1234, 6789).up() shouldBe PointLong(1234, 6788)
    }

    @Test
    fun down() {
        PointLong(0, 0).down() shouldBe PointLong(0, 1)
    }

    @Test
    fun manhattanDistance() {
        PointLong(10, 10).manhattanDistance(PointLong(-10, -10)) shouldBe 40
        PointLong(-10, -10).manhattanDistance(PointLong(10, 10)) shouldBe 40
    }
}