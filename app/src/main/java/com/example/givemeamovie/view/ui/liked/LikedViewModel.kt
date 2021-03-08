package com.example.givemeamovie.view.ui.liked

import androidx.lifecycle.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.repository.LikedRepository
import com.example.givemeamovie.repository.MovieWatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedViewModel @Inject constructor(
    private val likedRepository: LikedRepository,
    private val movieWatchListRepository: MovieWatchListRepository
): ViewModel() {

    private lateinit var _movieLibraries: LiveData<List<MovieLibrary>>
    val movieLibraries: LiveData<List<MovieLibrary>> = _movieLibraries

    fun getAvailableLibraries() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieLibraries = likedRepository.getAvailableLibraries().asLiveData()
        }
    }

    fun getMoviesInLibrary(
            library_name: String,
            onError: (String?) -> Unit
    ) = flow {
        try {
            val movieList: Flow<List<Movie>> = likedRepository.getMoviesInLibrary(library_name, onError)
            emit(movieList)
        } catch (ex: Throwable) {
            onError(ex.localizedMessage)
        }
    }
            .flowOn(Dispatchers.IO)
            .asLiveData()


}