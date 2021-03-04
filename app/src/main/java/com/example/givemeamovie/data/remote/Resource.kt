package com.example.givemeamovie.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)

    companion object {
        suspend fun <T> toResource(f: suspend () -> Response<T>) : Resource<T>  {
            try {
                val response = f()
                val result = response.body()
                if (response.isSuccessful && result != null) {
                    return Resource.Success(result)
                } else {
                    return Resource.Error(response.message())
                }
            } catch (ex: Exception) {
                return Resource.Error(ex.message ?: "An error occured")
            }
        }
    }
}
