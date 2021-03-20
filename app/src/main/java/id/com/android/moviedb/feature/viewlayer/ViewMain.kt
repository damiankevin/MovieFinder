package id.com.android.moviedb.feature.viewlayer

import id.com.android.moviedb.TypeStatus
import id.com.android.moviedb.model.ModelItemMovie


interface ViewMain : ViewBase {

    fun showLoadingProcess()
    fun showFailedLoadData(@TypeStatus.Status status : Long)
    fun showSuccessLoadData()
    fun showCollectionMovie(collectionMovie : ArrayList<ModelItemMovie>)
}