package io.github.serpro69.changelogen.api

import io.github.serpro69.changelogen.dto.GitRepository

/**
 * Fetches data from a git-platform provider, such as Github, Gitlab, etc.
 *
 * @param T fetcher api type
 */
interface Fetcher<T> {
    val api: T

    /**
     * Fetches the repository by it's [name] and returns as [GitRepository] object
     */
    fun getRepository(name: String): GitRepository
}

/**
 * Builder for [T] implementation of [Fetcher]
 */
interface FetcherBuilder<T : Fetcher<*>> {

    /**
     * Builds and returns an instance of [T]
     */
    fun build(): T
}

inline fun <T: Any, reified B : FetcherBuilder<T>> B.create(builder: B.() -> Unit) = this.apply(builder).build()
