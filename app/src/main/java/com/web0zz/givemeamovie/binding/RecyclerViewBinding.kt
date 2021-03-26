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
    @BindingAdapter("adapterMovieList")
    fun bindAdapterMovieList(view: RecyclerView, movie: List<Movie>) {
        view.adapter = MovieListAdapter(movie)
    }

    @JvmStatic
    @BindingAdapter("adapterMovieSection")
    fun bindAdapterMovieSection(view: RecyclerView, movieSection: List<MovieListSection>) {
        view.adapter = MovieListSectionAdapter(movieSection)
    }

    @JvmStatic
    @BindingAdapter("adapterWatchList")
    fun bindAdapterMovieSection(view: RecyclerView, dat: LibrarywithMovies) {
        view.adapter = MovieWatchListAdapter(dat)
    }

    @JvmStatic
    @BindingAdapter("adapterWatchListLine")
    fun bindAdapterWatchlist(view: RecyclerView, library: List<MovieLibrary>) {
        view.adapter = AddToWatchlistAdapter(library)
    }
}