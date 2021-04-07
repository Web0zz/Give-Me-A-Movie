package com.web0zz.givemeamovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.web0zz.givemeamovie.data.local.converter.IntegerListConverter
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibrary
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef
import com.web0zz.givemeamovie.model.entity.RecommendMovie

@Database(
    entities = [
        Movie::class,
        MovieLibrary::class,
        MovieLibraryCrossRef::class,
        RecommendMovie::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntegerListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao
    abstract fun movieDao(): MovieDao
    abstract fun movieLibraryWithMoviesDao(): MovieLibraryWithMoviesDao
    abstract fun likedMovieRecommendationDao(): LikedMovieRecommendationDao
}
