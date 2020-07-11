package io.github.serpro69.changelogen.api

import io.github.serpro69.changelogen.dto.Issue
import io.github.serpro69.changelogen.dto.GitTag
import io.github.serpro69.changelogen.dto.Milestone
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.kohsuke.github.GitHubBuilder
import java.util.*

class OctoFetcherTest : DescribeSpec({
    val repoName = "serpro69/changelogen-test"

    val fetcher by lazy {
        OctoFetcher.Builder().create {
            this.githubApi = GitHubBuilder.fromProperties(Options.getOpts("github.properties")).build()
        }
    }

    describe("OctoFetcher") {
        val repo = fetcher.getRepository(repoName)

        it("should fetch git repo by name") {
            assertSoftly {
                repo.owner shouldBe repoName.substringBefore("/")
                repo.name shouldBe repoName.substringAfter("/")
            }
        }

        context("GitRepository") {
            it("should contain all tags") {
                repo.tags shouldBe listOf(GitTag("v0.1.0"))
            }

            it("should contain all issues") {
                repo.issues shouldContainExactly listOf(
                    Issue(title="Rename nasty_bug_fix.txt to sometext"),
                    Issue(title="Rename text file"),
                    Issue(title="Issue without labels"),
                    Issue(title="Invalid bug"),
                    Issue(title="Add another file"),
                    Issue(title="Add even more text to nasty_bug_fix.txt"),
                    Issue(title="Add more text to nasty_bug_fix.txt"),
                    Issue(title="Fix for the nasty bug"),
                    Issue(title="Nasty bug"),
                    Issue(title="Missing readme")
                )
            }

            it("should contain all milestones") {
                repo.milestones shouldContainExactly listOf(
                    Milestone("v0.1.0"),
                    Milestone("v0.2.0")
                )
            }
        }
    }
})
