package br.com.rotacilio.desafioluizalabs.usecase

import br.com.rotacilio.desafioluizalabs.utils.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetRepositoriesUseCase(

) {
    suspend operator fun invoke(query: String, sort: String, page: Int) = flow<DataState<Any>> {
        emit(DataState.Loading)
    }.catch { e ->
        e.printStackTrace()
        emit(DataState.Error(e))
    }
}