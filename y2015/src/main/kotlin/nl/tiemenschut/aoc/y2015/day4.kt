package nl.tiemenschut.aoc.y2015

import nl.tiemenschut.aoc.lib.dsl.aoc
import nl.tiemenschut.aoc.lib.dsl.day
import java.math.BigInteger
import java.security.MessageDigest


fun main() {
    fun ByteArray.toHex(): String {
        val bi = BigInteger(1, this)
        return String.format("%0" + (this.size shl 1) + "x", bi)
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(input.toByteArray()).toHex()
    }

    aoc {
        puzzle { 2015 day 4 }

        part1 { input ->
            var i = 0L
            while (true) {
                val result = md5("$input${i++}")
                if (result.take(5) == "00000") return@part1 i-1
            }
        }

        part2 { input ->
            var i = 0L
            while (true) {
                val result = md5("$input${i++}")
                if (result.take(6) == "000000") return@part2 i-1
            }
        }
    }
}