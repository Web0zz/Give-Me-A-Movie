package com.example.givemeamovie.repository

import com.example.givemeamovie.data.local.LibraryDao
import com.example.givemeamovie.data.remote.service.MovieListService
import javax.inject.Inject

class HomeRepository @Inject constructor(
        private val movieListService: MovieListService,
        private val movieLibraryDao: LibraryDao
): Repository {
}