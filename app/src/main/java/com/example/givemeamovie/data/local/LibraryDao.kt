package com.example.givemeamovie.data.local

import androidx.room.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewMovieLibrary(library: MovieLibrary)
}