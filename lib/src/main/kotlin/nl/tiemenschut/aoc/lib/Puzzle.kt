package nl.tiemenschut.aoc.lib

infix fun Int.day(day: Int) = Puzzle(this, day)

data class Puzzle(val year: Int, val day: Int) {
    private val puzzleUrl = "/$year/day/$day"
    val inputUrl = "$puzzleUrl/input"
    val answerUrl = "$puzzleUrl/answer"
}