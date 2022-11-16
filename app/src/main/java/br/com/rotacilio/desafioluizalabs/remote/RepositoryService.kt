package br.com.rotacilio.desafioluizalabs.remote

import br.com.rotacilio.desafioluizalabs.model.RepositoriesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q", encoded = true) query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<RepositoriesApiResponse>
}