package io.github.serpro69.changelogen.api

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import okhttp3.OkHttpClient
import org.kohsuke.github.GitHubBuilder
import org.kohsuke.github.extras.okhttp3.OkHttpConnector
import java.util.*

class FetcherTest : DescribeSpec({

    describe("Builder") {
        context("OctoFetcher") {
            it("creates a new instance of OctoFetcher using GitHub-API-for-Java lib") {
                val connector = OkHttpConnector(OkHttpClient())

                val ghApi4J = GitHubBuilder.fromProperties(Properties())
                    .withConnector(connector)
                    .build()

                val fetcher = OctoFetcher.Builder().create {
                    this.githubApi = ghApi4J
                }

                assertSoftly {
                    fetcher.api shouldBe ghApi4J
                    fetcher.api.connector shouldBe connector
                }
            }
        }

        context("TanukiFetcher") {
            it("creates a new instance of TanukiFetcher") {
                val api = Any()

                val fetcher = TanukiFetcher.Builder().create {
                    this.gitlabApi = api
                }

                assertSoftly {
                    fetcher.api shouldBe api
                }
            }
        }
    }
})