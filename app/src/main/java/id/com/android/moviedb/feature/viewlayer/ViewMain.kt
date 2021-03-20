package id.com.android.moviedb.feature.viewlayer


interface ViewMain : ViewBase {

    fun showLoadingProcess()
    fun showFailedLoadData()
    fun showSuccessLoadData()
}