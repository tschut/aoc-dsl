package nl.tiemenschut.aoc.lib

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlin.io.path.Path
import kotlin.io.path.readLines

class AocService(private val root: String) {
    private val session: String = Path(".session").readLines().first()

    private fun <T> call(block: suspend HttpClient.() -> T): T = HttpClient().use { client ->
        runBlocking {
            client.block()
        }
    }

    fun getInput(puzzle: Puzzle): String = call {
        get {
            url(root + puzzle.inputUrl)
            cookie("session", session)
        }.bodyAsText()
    }

    fun submit(puzzle: Puzzle, answer: String): SubmitResponse = call {
        post {
            url(root + puzzle.answerUrl)
            cookie("session", session)
            contentType(ContentType.parse("application/x-www-form-urlencoded"))
            userAgent("Kotlin AoC DSL - Not yet on Github")
            setBody("""level=1&answer=$answer""")
        }.bodyToSubmissionResponse()
    }
}

private suspend fun HttpResponse.bodyToSubmissionResponse(): SubmitResponse {
    val response = bodyAsText().substringAfter("<article><p>").substringBefore("</p></article>")

    return when {
        response.contains("Did you already complete it?") -> SubmitResponse.Duplicate(response)
        response.contains("???") -> SubmitResponse.Correct(response)
        response.contains("That's not the right answer.") -> SubmitResponse.Incorrect(response)
        else -> SubmitResponse.Unknown(response)
    }
}
