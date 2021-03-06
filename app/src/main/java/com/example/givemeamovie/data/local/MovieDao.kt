package com.example.givemeamovie.data.local

import androidx.room.*
import com.example.givemeamovie.model.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<Movie>)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movie WHERE movie_id LIKE :movie_id")
    fun getMovie(movie_id: Int): Movie
}