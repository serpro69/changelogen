package io.github.serpro69.changelogen.api

import io.github.serpro69.changelogen.dto.Issue
import io.github.serpro69.changelogen.dto.GitRepository
import io.github.serpro69.changelogen.dto.GitTag
import io.github.serpro69.changelogen.dto.Milestone
import okhttp3.OkHttpClient
import org.kohsuke.github.GHIssueState
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub
import org.kohsuke.github.GitHubBuilder
import org.kohsuke.github.extras.okhttp3.OkHttpConnector

/**
 * Fetches data from a project hosted on GitHub
 */
class OctoFetcher private constructor(override val api: GitHub) : Fetcher<GitHub> {

    class Builder : FetcherBuilder<OctoFetcher> {
        var githubApi: GitHub = GitHubBuilder.fromProperties(Options.getOpts(""))
            .withConnector(OkHttpConnector(OkHttpClient()))
            .build()

        override fun build() = OctoFetcher(githubApi)
    }

    /**
     * Gets the github repository by [name] and returns as [GitRepository]
     *
     * @param name a GitHub's repository name represented as `owner/repository`
     */
    override fun getRepository(name: String): GitRepository {
        val ghRepo = api.getRepository(name)

        return GitRepository(
            owner = ghRepo.ownerName,
            name = ghRepo.name,
            tags = getAllTags(ghRepo),
            issues = getAllIssues(ghRepo),
            milestones = getAllMilestones(ghRepo)
        )
    }

    private fun getAllMilestones(ghRepo: GHRepository) = ghRepo.listMilestones(GHIssueState.ALL).map {
        Milestone(title = it.title)
    }

    private fun getAllIssues(ghRepo: GHRepository) = ghRepo.listIssues(GHIssueState.ALL).map {
        Issue(title = it.title)
    }

    private fun getAllTags(ghRepo: GHRepository) = ghRepo.listTags().map {
        GitTag(name = it.name)
    }
}

