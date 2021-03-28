package com.web0zz.givemeamovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.databinding.WatchlistListViewBinding
import com.web0zz.givemeamovie.model.entity.LibrarywithMovies
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibrary

class MovieWatchListAdapter(
        private val libraryAndMovies: LibrarywithMovies,
        private val clickListener: MovieListAdapter.MovieClickListener
): RecyclerView.Adapter<MovieWatchListAdapter.MovieWatchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieWatchListAdapter.MovieWatchListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WatchlistListViewBinding.inflate(layoutInflater)

        return MovieWatchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieWatchListAdapter.MovieWatchListViewHolder, position: Int) {
        with(holder.binding) {
            watchList = libraryAndMovies.Libraries[position]
            movieList = libraryAndMovies.Movies[position]
            clickListener = clickListener
            executePendingBindings()
        }
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(holder.binding.root.context, 3, GridLayoutManager.VERTICAL, false)
        holder.binding.watchlistMovieList.layoutManager = layoutManager
        holder.binding.watchlistMovieList.setHasFixedSize(true)
    }

    override fun getItemCount() = libraryAndMovies.Libraries.size
    class MovieWatchListViewHolder(val binding: WatchlistListViewBinding) :
            RecyclerView.ViewHolder(binding.root)
}