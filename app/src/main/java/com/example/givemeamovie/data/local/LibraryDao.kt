package com.example.givemeamovie.data.local

import androidx.room.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewMovieLibrary(library: MovieLibrary)

    @Query("SELECT * FROM movielibrary")
    fun getAvailableLibraries(): List<MovieLibrary>
}