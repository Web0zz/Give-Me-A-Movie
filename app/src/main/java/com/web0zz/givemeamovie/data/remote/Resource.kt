package com.web0zz.givemeamovie.data.remote

import retrofit2.Response

sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)

    companion object {
        suspend fun <T> toResource(f: suspend () -> Response<T>) : Resource<T>  {
            return try {
                val response = f()
                val result = response.body()
                if (response.isSuccessful && result != null) {
                    Resource.Success(result)
                } else {
                    Resource.Error(response.message())
                }
            } catch (ex: Exception) {
                Resource.Error(ex.message ?: "An error occured")
            }
        }
    }
}
