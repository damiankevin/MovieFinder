package id.com.android.moviedb.feature.interfacelayer

import id.com.android.moviedb.model.ModelItemMovie

interface InterfaceContentCollection {
    fun onContentFavourite(content: ModelItemMovie, position: Int)
    fun onContentUnFavourite(content: ModelItemMovie, position: Int)
    fun onContentSelected(content: ModelItemMovie)
}