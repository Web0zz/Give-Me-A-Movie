package com.example.givemeamovie.di

import com.example.givemeamovie.BASE_URL
import com.example.givemeamovie.BaseApplication
import com.example.givemeamovie.data.remote.NetworkInterceptor
import com.example.givemeamovie.data.remote.client.MovieDetailClient
import com.example.givemeamovie.data.remote.client.MovieListClient
import com.example.givemeamovie.data.remote.service.MovieDetailService
import com.example.givemeamovie.data.remote.service.MovieListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(BaseApplication::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptor() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDetailService(retrofit: Retrofit) = retrofit.create(MovieDetailService::class.java)

    @Singleton
    @Provides
    fun provideMovieDetailClient(movieDetailService: MovieDetailService): MovieDetailClient {
        return MovieDetailClient(movieDetailService)
    }

    @Singleton
    @Provides
    fun provideMovieListService(retrofit: Retrofit) = retrofit.create(MovieListService::class.java)

    @Singleton
    @Provides
    fun provideMovieListClient(movieListService: MovieListService): MovieListClient {
        return MovieListClient(movieListService)
    }

}
