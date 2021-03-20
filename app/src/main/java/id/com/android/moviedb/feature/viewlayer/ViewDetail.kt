package id.com.android.moviedb.feature.viewlayer

import id.com.android.moviedb.TypeStatus
import id.com.android.moviedb.model.ModelMovieDetail


interface ViewDetail : ViewBase {

    fun showFailedLoadData(@TypeStatus.Status status : Long)
    fun showDetailMovie(body: ModelMovieDetail?)
    fun showFavourite(hasFavourite: Boolean, body: ModelMovieDetail?)
}