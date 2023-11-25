package nl.tiemenschut.aoc.lib.dsl

import io.kotest.matchers.paths.shouldNotExist
import io.kotest.matchers.shouldBe
import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nl.tiemenschut.aoc.lib.TestBase
import nl.tiemenschut.aoc.lib.dsl.ResponseStatus.*
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.readText
import kotlin.io.path.writeText

class SubmitServiceTest : TestBase() {
    private val aocService: AocService = mockk()
    private val service = SubmitService(aocService)

    @Test
    fun `should not submit if puzzle was solved correctly before`() {
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
                      "answer": "answer",
                      "responseStatus": "CORRECT",
                      "responseText": "fullresponse"
                    }
                  ]
                }
            """.trimIndent()
            )

            service.submit(puzzle, 2, "yeah") shouldBe SubmitResponse(
                NOT_SUBMITTED,
                "Answer was not submitted to aoc for verification."
            )

            verify { aocService wasNot called }
        }
    }

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
                      "responseStatus": "INCORRECT",
                      "responseText": "fullresponse"
                    }
                  ]
                }
            """.trimIndent()
            )

            service.submit(puzzle, 2, "yeah") shouldBe SubmitResponse(INCORRECT, "krak")

            verify { aocService wasNot called }
        }
    }

    @Test
    fun `should save submission in cache`() {
        val puzzle = 2017 day 24

        every { aocService.submit(puzzle, 2, "answer") }.returns(SubmitResponse(CORRECT, "fullresponse"))

        service.submit(puzzle, 2, "answer") shouldBe SubmitResponse(CORRECT, "fullresponse")
        Path("data" + puzzle.answerFile).readText() shouldBe
                // language=JSON
                """
            {
              "submissions": [
                {
                  "level": 2,
                  "answer": "answer",
                  "responseStatus": "CORRECT",
                  "responseText": "fullresponse"
                }
              ]
            }
        """.trimIndent()
    }

    @Test
    fun `should not save submission in cache if response it TOO_RECENT`() {
        val puzzle = 2017 day 24

        every { aocService.submit(puzzle, 2, "answer") }.returns(SubmitResponse(TOO_RECENT, "fullresponse"))

        service.submit(puzzle, 2, "answer") shouldBe SubmitResponse(TOO_RECENT, "fullresponse")
        Path("data" + puzzle.answerFile).shouldNotExist()
    }
}