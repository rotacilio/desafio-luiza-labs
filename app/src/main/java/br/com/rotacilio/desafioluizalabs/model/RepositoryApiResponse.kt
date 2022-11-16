package br.com.rotacilio.desafioluizalabs.model

import com.squareup.moshi.Json

data class RepositoryApiResponse(
    val id: Long,
    val name: String,
    val owner: RepositoryOwnerApiResponse,
    val description: String,
    @Json(name = "pulls_url") val pullsUrl: String,
    val forks: Long,
    val watchers: Long
)