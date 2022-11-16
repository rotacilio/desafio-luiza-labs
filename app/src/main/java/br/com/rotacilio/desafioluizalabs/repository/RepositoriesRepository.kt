package br.com.rotacilio.desafioluizalabs.repository

import br.com.rotacilio.desafioluizalabs.remote.RepositoryService
import retrofit2.http.Query
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(
    private val service: RepositoryService
) {

    suspend fun getRepositories(query: String, sort: String, page: Int) =
        service.getRepositories(query, sort, page)
}