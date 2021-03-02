package com.example.givemeamovie.di

import com.example.givemeamovie.BASE_URL
import com.example.givemeamovie.BaseApplication
import com.example.givemeamovie.data.remote.NetworkInterceptor
import com.example.givemeamovie.data.remote.NetworkService
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
    fun provideFetchMovieDetailService(retrofit: Retrofit) = retrofit.create(NetworkService.FetchMovieDetail::class.java)

    @Singleton
    @Provides
    fun provideFetchMovieCast(retrofit: Retrofit) = retrofit.create(NetworkService.FetchMovieCast::class.java)

    @Singleton
    @Provides
    fun provideFetchVideos(retrofit: Retrofit) = retrofit.create(NetworkService.FetchVideos::class.java)

    @Singleton
    @Provides
    fun provideFetchKeywords(retrofit: Retrofit) = retrofit.create(NetworkService.FetchKeywords::class.java)

    @Singleton
    @Provides
    fun provideFetchSimilarMovie(retrofit: Retrofit) = retrofit.create(NetworkService.FetchSimilarMovie::class.java)

    @Singleton
    @Provides
    fun provideFetchRecommendationMovie(retrofit: Retrofit) = retrofit.create(NetworkService.FetchRecommendationMovie::class.java)

    @Singleton
    @Provides
    fun provideFetchSearchMovie(retrofit: Retrofit) = retrofit.create(NetworkService.FetchSearchMovie::class.java)

    @Singleton
    @Provides
    fun provideFetchNowPlayingMovie(retrofit: Retrofit) = retrofit.create(NetworkService.FetchNowPlayingMovie::class.java)

    @Singleton
    @Provides
    fun provideFetchPopularMovie(retrofit: Retrofit) = retrofit.create(NetworkService.FetchPopularMovie::class.java)

    @Singleton
    @Provides
    fun provideFetchTopRatedMovie(retrofit: Retrofit) = retrofit.create(NetworkService.FetchTopRatedMovie::class.java)

}
