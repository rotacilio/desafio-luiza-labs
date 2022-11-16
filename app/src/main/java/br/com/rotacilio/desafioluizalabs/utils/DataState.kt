package br.com.rotacilio.desafioluizalabs.utils

import retrofit2.HttpException

interface DataState<out R> {
    object Loading : DataState<Nothing>
    data class Success<out T>(val data: T) : DataState<T>
    data class Error(val exception: Throwable) : DataState<Nothing> {
        val statusCode: Int
            get() = if (this.exception is HttpException) this.exception.code() else -1
        val message: String
            get() = this.exception.message ?: "Unrecognized error"
    }
}