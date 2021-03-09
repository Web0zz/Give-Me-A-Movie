package com.example.givemeamovie.di

import android.content.Context
import androidx.room.Room
import com.example.givemeamovie.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MovieDB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLibraryDao(appDatabase: AppDatabase): LibraryDao {
        return appDatabase.libraryDao()
    }

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideMovieLibraryWithMoviesDao(appDatabase: AppDatabase): MovieLibraryWithMoviesDao {
        return appDatabase.movieLibraryWithMoviesDao()
    }

    @Singleton
    @Provides
    fun provideLikedMovieRecommendationDao(appDatabase: AppDatabase): LikedMovieRecommendationDao {
        return appDatabase.likedMovieRecommendationDao()
    }

}