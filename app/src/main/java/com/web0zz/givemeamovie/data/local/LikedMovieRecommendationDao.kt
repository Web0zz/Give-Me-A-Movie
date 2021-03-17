package com.web0zz.givemeamovie.data.local

import androidx.room.*
import com.web0zz.givemeamovie.model.entity.RecommendMovie

@Dao
interface LikedMovieRecommendationDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addMovieForRecommendation(recommendMovie: RecommendMovie)

    @Delete
    suspend fun deleteMovie(recommendMovie: RecommendMovie)

    @Query("SELECT * FROM recommendmovie ORDER BY movieId ASC LIMIT 1 ")
    suspend fun takeMovieToRecommend(): RecommendMovie
}