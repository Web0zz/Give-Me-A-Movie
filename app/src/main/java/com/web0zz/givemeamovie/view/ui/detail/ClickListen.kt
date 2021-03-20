package com.web0zz.givemeamovie.view.ui.detail

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef

object ClickListen {

    @JvmStatic
    fun bindlikeOnClick(
            mv: DetailViewModel,
            movie: Movie
    ) {
        val crossRef = MovieLibraryCrossRef("LIKED", mv.movieDetail.value!!.id)
        mv.checkIsThere(crossRef)
        if (mv.askMovieInLibrary.value!!) {
            mv.deleteFromLibrary(crossRef, movie)
        } else {
            mv.addToLibrary(crossRef, movie)
        }
    }
}