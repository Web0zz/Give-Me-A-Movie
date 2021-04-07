package com.web0zz.givemeamovie.di

import com.web0zz.givemeamovie.BASE_URL
import com.web0zz.givemeamovie.data.remote.NetworkInterceptor
import com.web0zz.givemeamovie.data.remote.service.MovieDetailService
import com.web0zz.givemeamovie.data.remote.service.MovieListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
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
    fun provideMovieListService(retrofit: Retrofit) = retrofit.create(MovieListService::class.java)
}
