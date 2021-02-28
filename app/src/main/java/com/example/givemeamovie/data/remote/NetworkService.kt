package com.example.givemeamovie.data.remote

import com.example.givemeamovie.BuildConfig
import com.example.givemeamovie.model.credits.Cast_and_Crew
import com.example.givemeamovie.model.keywords.Keyword_List
import com.example.givemeamovie.model.movie_detail.Detail
import com.example.givemeamovie.model.movie_detail.Video
import com.example.givemeamovie.model.movie_lists.Movie_list
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkService {

    interface fetchMovieDetail {

        @GET("movie/{movie_id}?api_key={api_key}&language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Detail>
    }

    interface fetchMovieCast {

        @GET("movie/{movie_id}/credits?api_key={api_key}&language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Cast_and_Crew>
    }

    interface fetchVideos {

        @GET("movie/{movie_id}/videos?api_key={api_key}&language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Video>
    }

    interface fetchKeywords {

        @GET("movie/{movie_id}/keywords?api_key={api_key}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Keyword_List>
    }

    interface fetchSimilarMovie {

        @GET("movie/{movie_id}/similar?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface fetchRecommendationMovie {

        @GET("movie/{movie_id}/recommendations?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface fetchMovieByKeyword {

        @GET("keyword/{keyword_id}/movies?api_key={api_key}&language=en-US&include_adult=false")
        suspend fun get(
            @Path("keyword_id") keyword_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Movie_list>
    }

    interface fetchSearchMovie {

        @GET("search/movie?api_key={api_key}&language=en-US&query={query}&page={page}&include_adult=false")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("query") query: String,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface fetchNowPlayingMovie {

        @GET("movie/now_playing?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface fetchPopularMovie {

        @GET("movie/popular?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface fetchTopRatedMovie {

        @GET("movie/top_rated?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }
}