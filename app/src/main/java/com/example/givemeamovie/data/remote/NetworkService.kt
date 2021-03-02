package com.example.givemeamovie.data.remote

import com.example.givemeamovie.BuildConfig
import com.example.givemeamovie.model.network.credits.Cast_and_Crew
import com.example.givemeamovie.model.network.keywords.Keyword_List
import com.example.givemeamovie.model.network.movie_detail.Detail
import com.example.givemeamovie.model.network.movie_detail.Video
import com.example.givemeamovie.model.network.movie_lists.Movie_list
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkService {

    interface FetchMovieDetail {

        @GET("movie/{movie_id}?language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int
        ): Response<Detail>
    }

    interface FetchMovieCast {

        @GET("movie/{movie_id}/credits?language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int
        ): Response<Cast_and_Crew>
    }

    interface FetchVideos {

        @GET("movie/{movie_id}/videos?language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int
        ): Response<Video>
    }

    interface FetchKeywords {

        @GET("movie/{movie_id}/keywords?")
        suspend fun get(
            @Path("movie_id") movie_id: Int
        ): Response<Keyword_List>
    }

    interface FetchSimilarMovie {

        @GET("movie/{movie_id}/similar?language=en-US&page={page}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchRecommendationMovie {

        @GET("movie/{movie_id}/recommendations?language=en-US&page={page}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchSearchMovie {

        @GET("search/movie?language=en-US&query={query}&page={page}&include_adult=false")
        suspend fun get(
            @Path("query") query: String,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchNowPlayingMovie {

        @GET("movie/now_playing?language=en-US&page={page}")
        suspend fun get(
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchPopularMovie {

        @GET("movie/popular?language=en-US&page={page}")
        suspend fun get(
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchTopRatedMovie {

        @GET("movie/top_rated?language=en-US&page={page}")
        suspend fun get(
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }
}