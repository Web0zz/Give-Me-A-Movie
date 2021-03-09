package com.example.givemeamovie.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.givemeamovie.model.entity.RecommendMovie

@Dao
interface LikedMovieRecommendationDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addMovieForRecommendation(recommendMovie: RecommendMovie)

    @Delete
    suspend fun deleteMovie(recommendMovie: RecommendMovie)

    @Query("SELECT * FROM recommendmovie ORDER BY movieId ASC LIMIT 1 ")
    suspend fun takeMovieToRecommend(): RecommendMovie
}