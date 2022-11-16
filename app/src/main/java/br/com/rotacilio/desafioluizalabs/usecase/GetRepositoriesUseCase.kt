package br.com.rotacilio.desafioluizalabs.usecase

import br.com.rotacilio.desafioluizalabs.model.RepositoriesApiResponse
import br.com.rotacilio.desafioluizalabs.repository.RepositoriesRepository
import br.com.rotacilio.desafioluizalabs.utils.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: RepositoriesRepository
) {
    suspend operator fun invoke(query: String, sort: String, page: Int) =
        flow {
            emit(DataState.Loading)
            val response = repository.getRepositories(query, sort, page)
            if (response.isSuccessful && response.body() != null) {
                emit(DataState.Success(data = response.body()!!))
            } else {
                emit(DataState.Error(HttpException(response)))
            }
        }.catch { e ->
            e.printStackTrace()
            emit(DataState.Error(e))
        }
}