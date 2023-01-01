package nl.tiemenschut.aoc.lib

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class AocServiceTest {
    private val mockUrl = "http://localhost:8089"
    private val service = AocService(mockUrl)

    companion object {
        private lateinit var aocMock: WireMockServer

        @JvmStatic
        @BeforeAll
        fun before() {
            aocMock = WireMockServer(WireMockConfiguration.options().port(8089)).also { it.start() }
        }

        @JvmStatic
        @AfterAll
        fun after() {
            aocMock.stop()
        }
    }

    @Test
    fun `should load input from aoc`() {
        aocMock.stubFor(
            get("/2023/day/10/input").withCookie("session", equalTo("testsession")).willReturn(
                aResponse().withBody("this is the puzzle input")
            )
        )

        assertThat(service.getInput(2023 day 10)).isEqualTo("this is the puzzle input")
    }

    @Test
    fun `should submit answer`() {
        val incorrectAnswerResponse = """
                <main>
                <article><p>That's not the right answer.  If you're stuck, make sure you're using the full input data; there are also some general tips on the <a href="/2015/about">about page</a>, or you can ask for hints on the <a href="https://www.reddit.com/r/adventofcode/" target="_blank">subreddit</a>.  Please wait one minute before trying again. (You guessed <span style="white-space:nowrap;"><code>foobar</code>.)</span> <a href="/2015/day/1">[Return to Day 1]</a></p></article>
                </main>"""

        aocMock.stubFor(
            post("/2023/day/11/answer")
                .withCookie("session", equalTo("testsession"))
                .withRequestBody(equalTo("level=1&answer=foobar")).willReturn(
                    aResponse().withBody(incorrectAnswerResponse)
                )
        )

        assertThat(service.submit(2023 day 11, "foobar")).isInstanceOf(SubmitResponse.Incorrect::class.java)
    }
}