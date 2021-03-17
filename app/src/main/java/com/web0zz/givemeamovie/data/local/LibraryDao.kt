package com.web0zz.givemeamovie.data.local

import androidx.room.*
import com.web0zz.givemeamovie.model.entity.MovieLibrary

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewMovieLibrary(library: MovieLibrary)

    @Query("SELECT * FROM movielibrary")
    suspend fun getAvailableLibraries(): List<MovieLibrary>

    @Query("UPDATE movielibrary SET library_size = library_size + :number WHERE library_Name = :movieLibraryName")
    suspend fun updateLibrarySize(number: Int, movieLibraryName: String)
}