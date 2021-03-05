package com.example.givemeamovie.di

import com.example.givemeamovie.data.local.LibraryDao
import com.example.givemeamovie.data.local.MovieDao
import com.example.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.example.givemeamovie.data.remote.service.MovieDetailService
import com.example.givemeamovie.data.remote.service.MovieListService
import com.example.givemeamovie.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDetailRepository(movieDetailService: MovieDetailService): Repository {
        return DetailRepository(movieDetailService)
    }

    @Singleton
    @Provides
    fun provideExploreRepository(
            movieListService: MovieListService,
            movieDao: MovieDao,
            croffRefDao: MovieLibraryWithMoviesDao
    ): Repository {
        return ExploreRepository(movieListService, movieDao, croffRefDao)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(movieListService: MovieListService): Repository {
        return HomeRepository(movieListService)
    }

    @Singleton
    @Provides
    fun provideLikedRepository(
            movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
            movieLibraryDao: LibraryDao,
            movieDao: MovieDao
    ): Repository {
        return LikedRepository(movieLibraryWithMoviesDao, movieLibraryDao, movieDao)
    }

    @Singleton
    @Provides
    fun provideMovieWatchListRepository(
            movieLibraryDao: LibraryDao,
            movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
            movieDao: MovieDao
    ): Repository {
        return MovieWatchListRepository(movieLibraryDao, movieLibraryWithMoviesDao, movieDao)
    }
}