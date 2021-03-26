package com.web0zz.givemeamovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.databinding.WatchlistPartViewBinding
import com.web0zz.givemeamovie.model.entity.MovieLibrary

class AddToWatchlistAdapter(
        private val items: List<MovieLibrary>
) : RecyclerView.Adapter<AddToWatchlistAdapter.WatchlistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WatchlistPartViewBinding.inflate(layoutInflater)

        return WatchlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        with(holder.binding) {
            watchlist = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class WatchlistViewHolder(val binding: WatchlistPartViewBinding) :
            RecyclerView.ViewHolder(binding.root)
}