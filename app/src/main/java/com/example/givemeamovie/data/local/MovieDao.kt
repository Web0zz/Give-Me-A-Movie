package com.example.givemeamovie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.givemeamovie.model.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<Movie>)

    @Query("SELECT * FROM Movie WHERE movie_id LIKE :movie_id")
    fun getMovie(movie_id: Int): Movie
}