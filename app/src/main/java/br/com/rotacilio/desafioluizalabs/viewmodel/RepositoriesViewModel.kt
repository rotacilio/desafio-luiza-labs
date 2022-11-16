package br.com.rotacilio.desafioluizalabs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rotacilio.desafioluizalabs.model.RepositoryApiResponse
import br.com.rotacilio.desafioluizalabs.usecase.GetRepositoriesUseCase
import br.com.rotacilio.desafioluizalabs.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<RepositoryApiResponse>>(emptyList())
    val repositories: StateFlow<List<RepositoryApiResponse>>
        get() = _repositories.asStateFlow()

    private var _currentPage = 1

    fun getRepositories(
        query: String,
        sort: String
    ) {
        viewModelScope.launch {
            getRepositoriesUseCase(
                query = query,
                sort = sort,
                page = _currentPage,
            ).collectLatest { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _repositories.value = _repositories.value.plus(dataState.data.items)
                        _currentPage += 1
                    }
                }
            }
        }
    }

    init {
        getRepositories("language:Kotlin", "stars")
    }
}