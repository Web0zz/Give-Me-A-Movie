package com.web0zz.givemeamovie.data.local

import androidx.room.*
import com.web0zz.givemeamovie.model.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMovieList(movies: List<Movie>)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movie WHERE movie_id = :movie_id")
    suspend fun getMovie(movie_id: Int): Movie
}