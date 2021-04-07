package com.web0zz.givemeamovie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.R
import com.web0zz.givemeamovie.databinding.WatchlistListViewBinding
import com.web0zz.givemeamovie.model.entity.LibrarywithMovies

class MovieWatchListAdapter(
    private val libraryAndMovies: LibrarywithMovies,
    private val onClickListener: MovieListAdapter.MovieClickListener
) : RecyclerView.Adapter<MovieWatchListAdapter.MovieWatchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieWatchListAdapter.MovieWatchListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WatchlistListViewBinding.inflate(layoutInflater)

        return MovieWatchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieWatchListAdapter.MovieWatchListViewHolder, position: Int) {
        with(holder.binding) {
            watchList = libraryAndMovies.Libraries[position]
            movieList = libraryAndMovies.Movies[position]
            clickListener = onClickListener
            executePendingBindings()
            expandButton.setOnClickListener {
                if (watchlistMovieList.visibility == View.VISIBLE) {
                    watchlistMovieList.visibility = View.GONE
                    expandButton.setImageResource(R.drawable.expand_more_icon)
                } else {
                    watchlistMovieList.visibility = View.VISIBLE
                    expandButton.setImageResource(R.drawable.expand_less_icon)
                }
            }
        }
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(holder.binding.root.context, 3, GridLayoutManager.VERTICAL, false)
        holder.binding.watchlistMovieList.layoutManager = layoutManager
        holder.binding.watchlistMovieList.setHasFixedSize(true)
    }

    override fun getItemCount() = libraryAndMovies.Libraries.size
    class MovieWatchListViewHolder(val binding: WatchlistListViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
