package com.example.givemeamovie.data.local

import androidx.room.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef

@Dao
interface MovieLibraryWithMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie_library: MovieLibraryCrossRef)

    @Delete
    fun deleteMovie(movie: Movie)

    @Transaction
    @Query("SELECT * FROM movielibrarycrossref WHERE library_Name LIKE :library_name")
    fun getMovies(library_name: String): List<MovieLibraryCrossRef>
}