package com.example.givemeamovie.repository

import com.example.givemeamovie.data.remote.service.MovieListService
import javax.inject.Inject

class ExploreRepository @Inject constructor(
        private val movieListService: MovieListService,
): Repository {

}