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

    interface FetchMovieDetail {

        @GET("movie/{movie_id}?api_key={api_key}&language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Detail>
    }

    interface FetchMovieCast {

        @GET("movie/{movie_id}/credits?api_key={api_key}&language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Cast_and_Crew>
    }

    interface FetchVideos {

        @GET("movie/{movie_id}/videos?api_key={api_key}&language=en-US")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Video>
    }

    interface FetchKeywords {

        @GET("movie/{movie_id}/keywords?api_key={api_key}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Keyword_List>
    }

    interface FetchSimilarMovie {

        @GET("movie/{movie_id}/similar?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchRecommendationMovie {

        @GET("movie/{movie_id}/recommendations?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("movie_id") movie_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchMovieByKeyword {

        @GET("keyword/{keyword_id}/movies?api_key={api_key}&language=en-US&include_adult=false")
        suspend fun get(
            @Path("keyword_id") keyword_id: Int,
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY
        ): Response<Movie_list>
    }

    interface FetchSearchMovie {

        @GET("search/movie?api_key={api_key}&language=en-US&query={query}&page={page}&include_adult=false")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("query") query: String,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchNowPlayingMovie {

        @GET("movie/now_playing?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchPopularMovie {

        @GET("movie/popular?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }

    interface FetchTopRatedMovie {

        @GET("movie/top_rated?api_key={api_key}&language=en-US&page={page}")
        suspend fun get(
            @Path("api_key") API_KEY: String = BuildConfig.API_KEY,
            @Path("page") page: Int = 1
        ): Response<Movie_list>
    }
}