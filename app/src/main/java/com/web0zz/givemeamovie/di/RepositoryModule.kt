package com.web0zz.givemeamovie.di

import com.web0zz.givemeamovie.data.local.LibraryDao
import com.web0zz.givemeamovie.data.local.LikedMovieRecommendationDao
import com.web0zz.givemeamovie.data.local.MovieDao
import com.web0zz.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.web0zz.givemeamovie.data.remote.service.MovieDetailService
import com.web0zz.givemeamovie.data.remote.service.MovieListService
import com.web0zz.givemeamovie.repository.*
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
    fun provideDetailRepository(
        movieDetailService: MovieDetailService,
        movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
        movieDao: MovieDao
    ): Repository {
        return DetailRepository(movieDetailService, movieLibraryWithMoviesDao, movieDao)
    }

    @Singleton
    @Provides
    fun provideExploreRepository(
        movieListService: MovieListService,
        movieDao: MovieDao,
        croffRefDao: MovieLibraryWithMoviesDao,
        likedMovieRecommendationDao: LikedMovieRecommendationDao
    ): Repository {
        return ExploreRepository(movieListService, movieDao, croffRefDao, likedMovieRecommendationDao)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(movieListService: MovieListService): Repository {
        return HomeRepository(movieListService)
    }

    @Singleton
    @Provides
    fun provideFavoriteRepository(
        movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
        movieLibraryDao: LibraryDao,
        movieDao: MovieDao
    ): Repository {
        return FavoriteRepository(movieLibraryWithMoviesDao, movieLibraryDao, movieDao)
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
