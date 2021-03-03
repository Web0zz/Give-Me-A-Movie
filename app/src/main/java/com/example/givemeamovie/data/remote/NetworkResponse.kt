package com.example.givemeamovie.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.Exception

sealed class NetworkResponse<out T> {

    class Success<T>(response: Response<T>) : NetworkResponse<T>() {
        val data: T? = response.body()
    }

    sealed class Failure<out T> {
        class Error<out T>(response: Response<out T>) : NetworkResponse<T>() {
            val responseBody: ResponseBody? = response.errorBody()?.apply { close() }
            val code: Int = response.code()
        }
        class Exception<out T>(throwable: Throwable) : NetworkResponse<T>() {
            val message: String? = throwable.localizedMessage
        }
    }

    companion object {
        fun <T> error(throwable: Throwable) = Failure.Exception<T>(throwable)

        fun <T> factory(f: () -> Response<T>): NetworkResponse<T> {
            return try {
                val response = f()
                if(response.isSuccessful) {
                    Success(response)
                } else {
                    Failure.Error(response)
                }
            } catch (ex: Exception) {
                Failure.Exception(ex)
            }
        }
    }
}
