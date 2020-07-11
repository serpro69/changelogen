package io.github.serpro69.changelogen.dto

/**
 * A generic git repository.
 */
data class GitRepository(
    val owner: String,
    val name: String,
    val tags: List<GitTag>,
    val issues: List<Issue>,
    val milestones: List<Milestone>
)
