package com.web0zz.givemeamovie.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.model.entity.LibrarywithMovies
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibrary
import com.web0zz.givemeamovie.model.network.credits.Cast
import com.web0zz.givemeamovie.model.network.movie_lists.MovieListSection
import com.web0zz.givemeamovie.view.adapter.*

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapterCastList")
    fun bindAdapterCastList(view: RecyclerView, cast: List<Cast>) {
        view.adapter = CastListAdapter(cast)
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterMovieList", "onClickListener"])
    fun bindAdapterMovieList(view: RecyclerView, movie: List<Movie>, clickListener: MovieListAdapter.MovieClickListener) {
        view.adapter = MovieListAdapter(movie, clickListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterMovieSection", "onClickListener"])
    fun bindAdapterMovieSection(view: RecyclerView, movieSection: List<MovieListSection>, onClickListener: MovieListAdapter.MovieClickListener) {
        view.adapter = MovieListSectionAdapter(movieSection, onClickListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterWatchList", "onClickListener"])
    fun bindAdapterMovieSection(view: RecyclerView, data: LibrarywithMovies, onClickListener: MovieListAdapter.MovieClickListener) {
        view.adapter = MovieWatchListAdapter(data, onClickListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterWatchListLine", "onClickListener"])
    fun bindAdapterWatchlist(view: RecyclerView, library: List<MovieLibrary>, onClickListenerToListClickListener: AddToWatchlistAdapter.WatchListAddToListClickListener) {
        view.adapter = AddToWatchlistAdapter(library, onClickListenerToListClickListener)
    }
}