package com.example.givemeamovie.data.local

import androidx.room.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewMovieLibrary(library: MovieLibrary)

    @Query("SELECT * FROM movielibrary")
    suspend fun getAvailableLibraries(): List<MovieLibrary>

    @Query("UPDATE movielibrary SET library_size = library_size + :number WHERE library_Name = :_library_name")
    suspend fun updateLibrarySize(number: Int, _library_name: String)
}