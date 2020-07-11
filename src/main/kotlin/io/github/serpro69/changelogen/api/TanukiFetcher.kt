package io.github.serpro69.changelogen.api

import io.github.serpro69.changelogen.dto.GitRepository
import io.github.serpro69.changelogen.dto.GitTag
import okhttp3.OkHttpClient
import org.kohsuke.github.GitHub
import org.kohsuke.github.GitHubBuilder
import org.kohsuke.github.extras.okhttp3.OkHttpConnector

/**
 * Fetches data from a project hosted on GitHub
 */
class TanukiFetcher private constructor(override val api: Any) : Fetcher<Any> {

    class Builder : FetcherBuilder<TanukiFetcher> {
        var gitlabApi = Any()

        override fun build() = TanukiFetcher(gitlabApi)
    }

    override fun getRepository(name: String): GitRepository {
        TODO("Not yet implemented")
    }
}

