package com.example.givemeamovie.data.remote

import retrofit2.Call
import retrofit2.Callback

fun <T> Call<T>.transform(onResult: (response: NetworkResponse<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            onResult(NetworkResponse.factory { response })
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            onResult(NetworkResponse.error(throwable))
        }
    })
}
