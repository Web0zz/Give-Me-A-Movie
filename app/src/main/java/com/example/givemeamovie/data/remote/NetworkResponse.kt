package com.example.givemeamovie.data.remote.apiResult

import java.io.IOException

sealed class NetworkResponse<out T: Any, out U: Any> {
    data class Success<T: Any>(val body: T) : NetworkResponse<T, Nothing>()
    data class Failure<U: Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()
    data class UnkownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}
