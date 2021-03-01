package com.example.givemeamovie.model.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef

data class MovieLibraryPair(
        @Embedded
        val movie_Library: MovieLibrary,
        @Relation(
                parentColumn = "library_Name",
                entityColumn = "movie_id",
                associateBy = Junction(MovieLibraryCrossRef::class)
        )
        val movies: List<Movie>
)
