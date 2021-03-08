package com.example.givemeamovie.data.local

import androidx.room.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef

@Dao
interface MovieLibraryWithMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie_Ref: MovieLibraryCrossRef)

    @Delete
    suspend fun deleteMovie(movie_Ref: MovieLibraryCrossRef)

    @Transaction
    @Query("SELECT * FROM movielibrarycrossref WHERE library_Name = :library_name")
    suspend fun getMovies(library_name: String): List<MovieLibraryCrossRef>
}