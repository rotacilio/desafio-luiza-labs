package br.com.rotacilio.desafioluizalabs.model

import com.squareup.moshi.Json

data class RepositoriesApiResponse(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "incomplete_results") val incompleteResults: Boolean,
    val items: List<RepositoryApiResponse>
)