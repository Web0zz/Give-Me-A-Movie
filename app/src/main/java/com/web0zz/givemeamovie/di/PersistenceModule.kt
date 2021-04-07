package com.web0zz.givemeamovie.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.web0zz.givemeamovie.data.local.*
import com.web0zz.givemeamovie.model.entity.MovieLibrary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton
import javax.security.auth.callback.Callback

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
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    provideDatabase(appContext).libraryDao().addNewMovieLibrary(MovieLibrary("LIKED", 0, "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg"))
                }
            }
        }).build()
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
