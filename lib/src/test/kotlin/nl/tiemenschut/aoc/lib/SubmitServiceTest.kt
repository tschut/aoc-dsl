package nl.tiemenschut.aoc.lib

import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nl.tiemenschut.aoc.lib.ResponseStatus.CORRECT
import nl.tiemenschut.aoc.lib.ResponseStatus.INCORRECT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.readText
import kotlin.io.path.writeText

class SubmitServiceTest : TestBase() {
    private val aocService: AocService = mockk()
    private val service = SubmitService(aocService)

    @Test
    fun `should get submission from cache if it exists`() {
        val puzzle = 2020 day 10
        Path("data" + puzzle.answerFile).run {
            parent.createDirectories()
            toFile().createNewFile()
            //language=JSON
            writeText(
                """
                {
                  "submissions": [
                    {
                      "level": 2,
                      "answer": "yeah",
                      "responseStatus": "INCORRECT",
                      "responseText": "krak"
                    },
                    {
                      "level": 2,
                      "answer": "answer",
                      "responseStatus": "CORRECT",
                      "responseText": "fullresponse"
                    }
                  ]
                }
            """.trimIndent()
            )

            assertThat(service.submit(puzzle, 2, "yeah")).isEqualTo(SubmitResponse(INCORRECT, "krak"))

            verify { aocService wasNot called }
        }
    }

    @Test
    fun `should save submission in cache`() {
        val puzzle = 2017 day 24

        every { aocService.submit(puzzle, 2, "answer") }.returns(SubmitResponse(CORRECT, "fullresponse"))

        assertThat(service.submit(puzzle, 2, "answer")).isEqualTo(SubmitResponse(CORRECT, "fullresponse"))
        assertThat(Path("data" + puzzle.answerFile).readText()).isEqualTo(
            """
            {"submissions":[{"level":2,"answer":"answer","responseStatus":"CORRECT","responseText":"fullresponse"}]}
        """.trimIndent()
        )
    }
}