package br.com.rotacilio.desafioluizalabs.utils

import retrofit2.HttpException

interface DataState<out R> {
    object Loading : DataState<Nothing>
    data class Success<out T>(val data: T) : DataState<T>
    data class Error(val t: Throwable) : DataState<Nothing> {
        val statusCode: Int
            get() = if (this.t is HttpException) this.t.code() else -1
    }
}