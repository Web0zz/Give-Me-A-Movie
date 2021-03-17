package com.web0zz.givemeamovie.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.model.network.credits.Cast
import com.web0zz.givemeamovie.view.adapter.CastListAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapterCastList")
    fun bindAdapterCastList(view: RecyclerView, cast: List<Cast>) {
        view.adapter = CastListAdapter(cast)
    }
}