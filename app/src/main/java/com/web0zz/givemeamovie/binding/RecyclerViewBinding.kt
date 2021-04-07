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
    fun bindAdapterCastList(view: RecyclerView, cast: List<Cast>?) {
        if (cast != null) {
            view.adapter = CastListAdapter(cast)
        } else {
            view.adapter = CastListAdapter(emptyList())
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterMovieSection", "onClickListener"])
    fun bindAdapterMovieListSection(view: RecyclerView, data: List<MovieListSection>?, onClickListener: MovieListAdapter.MovieClickListener) {
        if (data != null) {
            view.adapter = MovieListSectionAdapter(data, onClickListener)
        } else {
            view.adapter = MovieListSectionAdapter(emptyList(), onClickListener)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterMovieList", "onClickListener"])
    fun bindAdapterMovieList(view: RecyclerView, movie_list: List<Movie>?, clickListener: MovieListAdapter.MovieClickListener) {
        if (movie_list != null) {
            view.adapter = MovieListAdapter(movie_list, clickListener)
        } else {
            view.adapter = MovieListAdapter(emptyList(), clickListener)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterWatchList", "onClickListener"])
    fun bindAdapterMovieSection(view: RecyclerView, data: LibrarywithMovies?, onClickListener: MovieListAdapter.MovieClickListener) {
        if (data != null) {
            view.adapter = MovieWatchListAdapter(data, onClickListener)
        } else {
            view.adapter = MovieWatchListAdapter(LibrarywithMovies(emptyList(), emptyList()), onClickListener)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["adapterWatchListLine", "onClickListener"])
    fun bindAdapterWatchlist(view: RecyclerView, library: List<MovieLibrary>?, onClickListenerToListClickListener: AddToWatchlistAdapter.WatchListAddToListClickListener) {
        if (library != null) {
            view.adapter = AddToWatchlistAdapter(library, onClickListenerToListClickListener)
        } else {
            view.adapter = AddToWatchlistAdapter(emptyList(), onClickListenerToListClickListener)
        }
    }
}
