package com.example.givemeamovie.data.local

import androidx.room.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef

@Dao
interface MovieLibraryWithMoviesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(movie_Ref: MovieLibraryCrossRef)

    @Delete
    suspend fun deleteMovie(movie_Ref: MovieLibraryCrossRef)

    @Transaction
    @Query("SELECT * FROM movielibrarycrossref WHERE library_Name = :library_name")
    suspend fun getMovies(library_name: String): List<MovieLibraryCrossRef>

    @Query("SELECT EXISTS(SELECT * FROM movielibrarycrossref WHERE movie_id = :movieId AND library_Name = :movieLibrary)")
    suspend fun checkIsThere(movieId: Int, movieLibrary: String): Boolean
}