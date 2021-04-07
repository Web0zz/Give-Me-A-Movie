package com.web0zz.givemeamovie.view.ui.detail

import androidx.lifecycle.*
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef
import com.web0zz.givemeamovie.model.network.credits.Cast_and_Crew
import com.web0zz.givemeamovie.model.network.movie_detail.Detail
import com.web0zz.givemeamovie.model.network.movie_detail.Video
import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import com.web0zz.givemeamovie.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {

    private var _movieDetail = MutableLiveData<Detail>()
    val movieDetail: LiveData<Detail> = _movieDetail

    private var _movieCast = MutableLiveData<Cast_and_Crew>()
    val movieCast: LiveData<Cast_and_Crew> = _movieCast

    private var _movieVideo = MutableLiveData<Video>()
    val movieVideo: LiveData<Video> = _movieVideo

    private var _similarMovies = MutableLiveData<Movie_list>()
    val similarMovies: LiveData<Movie_list> = _similarMovies

    private var _askMovieInLibrary = MutableLiveData<Boolean>()

    // Main function for Ui
    fun likeAction(movie: Movie): Boolean {
        val crossRef = MovieLibraryCrossRef("LIKED", movieDetail.value!!.id)
        checkIsThere(crossRef)
        return if (_askMovieInLibrary.value!!) {
            deleteFromLibrary(crossRef, movie)
            false
        } else {
            addToLibrary(crossRef, movie)
            true
        }
    }

    fun getMovieDetails(
        movie_id: Int,
        onError: (String?) -> Unit
    ) {
        fetchMovieDetail(movie_id, onError)
        fetchMovieCast(movie_id, onError)
        fetchVideos(movie_id, onError)
        fetchVideos(movie_id, onError)
        fetchSimilarMovies(movie_id, onError)
    }
    // ****

    private fun addToLibrary(crossRef: MovieLibraryCrossRef, movie: Movie) {
        viewModelScope.launch {
            detailRepository.addToLibrary(crossRef, movie)
        }
    }

    private fun deleteFromLibrary(crossRef: MovieLibraryCrossRef, movie: Movie) {
        viewModelScope.launch {
            detailRepository.deleteFromLibrary(crossRef, movie)
        }
    }

    private fun checkIsThere(crossRef: MovieLibraryCrossRef) {
        viewModelScope.launch {
            _askMovieInLibrary.postValue(detailRepository.checkIsThere(crossRef))
        }
    }

    private fun fetchMovieDetail(
        movie_id: Int,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val detail = detailRepository.fetchMovieDetail(movie_id)) {
                is Resource.Success -> _movieDetail.postValue(detail.data!!)
                is Resource.Error -> onError(detail.message)
            }
        }
    }

    private fun fetchMovieCast(
        movie_id: Int,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val cast = detailRepository.fetchMovieCast(movie_id)) {
                is Resource.Success -> _movieCast.postValue(cast.data!!)
                is Resource.Error -> onError(cast.message)
            }
        }
    }

    private fun fetchVideos(
        movie_id: Int,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val videos = detailRepository.fetchVideos(movie_id)) {
                is Resource.Success -> _movieVideo.postValue(videos.data!!)
                is Resource.Error -> onError(videos.message)
            }
        }
    }

    private fun fetchSimilarMovies(
        movie_id: Int,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val movies = detailRepository.fetchSimilarMovies(movie_id)) {
                is Resource.Success -> _similarMovies.postValue(movies.data!!)
                is Resource.Error -> onError(movies.message)
            }
        }
    }
}
