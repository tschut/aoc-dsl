package nl.tiemenschut.aoc.lib.dsl

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import nl.tiemenschut.aoc.lib.dsl.ResponseStatus.*

class AocService(private val root: String) {
    private val session = javaClass.classLoader.getResource(".session")?.readText()
        ?: throw SessionInvalidException("Could not open .session resource.")

    private fun <T> call(block: suspend HttpClient.() -> T): T = HttpClient {
        install(UserAgent) {
            agent = "Kotlin AoC DSL - https://github.com/tschut/aoc-dsl"
        }
    }.use { client ->
        runBlocking {
            client.block()
        }
    }

    fun getInput(puzzle: Puzzle): String = call {
        get {
            url(root + puzzle.inputUrl)
            cookie("session", session)
        }.bodyAsText().trim()
    }

    fun submit(puzzle: Puzzle, level: Int, answer: String): SubmitResponse = call {
        post {
            url(root + puzzle.answerUrl)
            cookie("session", session)
            contentType(ContentType.parse("application/x-www-form-urlencoded"))
            setBody("""level=$level&answer=$answer""")
        }.bodyToSubmissionResponse()
    }
}

private suspend fun HttpResponse.bodyToSubmissionResponse(): SubmitResponse {
    val response = bodyAsText().substringAfter("<article><p>").substringBefore("</p></article>")

    val status = when {
        response.contains("You gave an answer too recently") -> TOO_RECENT
        response.contains("Did you already complete it?") -> DUPLICATE
        response.contains("That's the right answer") -> CORRECT
        response.contains("That's not the right answer") -> INCORRECT
        else -> UNKNOWN
    }

    return SubmitResponse(status, response)
}
