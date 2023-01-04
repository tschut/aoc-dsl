package nl.tiemenschut.aoc.lib

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.io.path.*

@Serializable
data class SubmitResponseDto(
    val level: Int,
    val answer: String,
    val responseStatus: ResponseStatus,
    val responseText: String,
) {
    fun toSubmitResponse() = SubmitResponse(responseStatus, responseText)
}

@Serializable
data class SubmitResponseCacheDto(val submissions: List<SubmitResponseDto>)

class SubmitService(private val aocService: AocService) {
    private fun Puzzle.responseCacheFile() = Path(CACHE_ROOT + answerFile)

    fun submit(puzzle: Puzzle, level: Int, answer: String): SubmitResponse {
        return getSubmitFromCache(puzzle, level, answer)?.also {
            println("You submitted this answer before, not submitting it again!")
        } ?: aocService.submit(puzzle, level, answer).also { put(puzzle, level, answer, it) }
    }

    private fun getSubmitFromCache(puzzle: Puzzle, level: Int, answer: String): SubmitResponse? =
        readCache(puzzle).submissions
            .find { it.level == level && it.answer == answer }
            ?.toSubmitResponse()

    private fun readCache(puzzle: Puzzle) = puzzle.responseCacheFile()
        .takeIf { it.exists() }
        ?.readText()
        ?.takeIf { it.isNotBlank() }
        ?.let { Json.decodeFromString<SubmitResponseCacheDto>(it) }
        ?: SubmitResponseCacheDto(emptyList())

    private fun writeCache(puzzle: Puzzle, content: SubmitResponseCacheDto) {
        puzzle.responseCacheFile().run {
            parent.createDirectories()
            toFile().createNewFile()
            writeText(Json.encodeToString(content))
        }
    }

    private fun put(puzzle: Puzzle, level: Int, answer: String, response: SubmitResponse) {
        val cache = readCache(puzzle)
        val submitResponseDto = SubmitResponseDto(level, answer, response.status, response.responseText)

        writeCache(puzzle, cache.copy(submissions = cache.submissions.toMutableList() + submitResponseDto))
    }
}