package com.web0zz.givemeamovie.data.local

import androidx.room.*
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef

@Dao
interface MovieLibraryWithMoviesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(movie_Ref: MovieLibraryCrossRef)

    @Delete
    suspend fun deleteMovie(movie_Ref: MovieLibraryCrossRef)

    @Transaction
    @Query("SELECT * FROM movielibrarycrossref WHERE library_Name = :name")
    suspend fun getMovies(name: String): List<MovieLibraryCrossRef>

    @Query("SELECT EXISTS(SELECT * FROM movielibrarycrossref WHERE movie_id = :movieId AND library_Name = :movieLibrary)")
    suspend fun checkIsThere(movieId: Int, movieLibrary: String): Boolean
}
