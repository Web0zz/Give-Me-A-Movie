package com.example.givemeamovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.givemeamovie.data.local.converter.IntegerListConverter
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef
import com.example.givemeamovie.model.entity.RecommendMovie

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
abstract class AppDatabase: RoomDatabase() {
    abstract fun libraryDao(): LibraryDao
    abstract fun movieDao(): MovieDao
    abstract fun movieLibraryWithMoviesDao(): MovieLibraryWithMoviesDao
    abstract fun likedMovieRecommendationDao(): LikedMovieRecommendationDao
}