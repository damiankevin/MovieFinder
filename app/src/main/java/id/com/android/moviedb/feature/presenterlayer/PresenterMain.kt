package id.com.android.moviedb.feature.presenterlayer

import android.util.Log
import id.com.android.moviedb.TypeScreen
import id.com.android.moviedb.TypeStatus
import id.com.android.moviedb.controller.ControllerEndpoint
import id.com.android.moviedb.feature.viewlayer.ViewMain
import id.com.android.moviedb.model.ModelItemMovie
import id.com.android.moviedb.model.ModelMovie
import id.com.android.moviedb.repository.RepositoryContent
import id.com.android.moviedb.tools.UtilConstant
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PresenterMain : PresenterBase<ViewMain> {
    private var collectionPersistance: ArrayList<Int> = ArrayList()
    private var serviceApi : ControllerEndpoint
    private var repositoryContent: RepositoryContent?

    @Inject
    constructor(controllerServiceApi: ControllerEndpoint, repositoryContent: RepositoryContent) {
        this.serviceApi = controllerServiceApi
        this.repositoryContent = repositoryContent
    }

    fun viewCreated(currentScreen: String) {
        pageLoaded(currentScreen,UtilConstant.INITIAL_START_PAGE)
    }

    fun pageLoaded(currentScreen: String, pagePosition: Int) {

        if(currentScreen==TypeScreen.FAVOURITE){
            doAsync {
                val collection = repositoryContent?.contentDao()?.allContent()
                var arrayCollection : ArrayList<ModelItemMovie> = ArrayList()
                for(i in collection!!.indices){
                    arrayCollection.add(collection[i])
                }

                uiThread {
                    setSuccessLoadData()
                    if(collection?.size==0){
                        setErrorStatus(TypeStatus.ERROR_DATA_EMPTY)
                    }else{
                        setFromFavourite(arrayCollection)
                    }
                }
            }
        }else{
            serviceApi.getUpcoming(currentScreen,pagePosition,UtilConstant.API_KEY).enqueue(object :
                Callback<ModelMovie> {
                override fun onResponse(call: Call<ModelMovie>, response: Response<ModelMovie>) {
                    if(response.isSuccessful){
                        Log.d("sukses","sukses")
                        checkNotNull(response.body()?.results){
                            setErrorStatus(TypeStatus.ERROR_DATA_EMPTY)
                            return
                        }


                        doAsync {
                            val collectionUrl = repositoryContent?.contentDao()?.allContentIdFavourite(true)
                            uiThread {
                                collectionPersistance.clear()
                                collectionPersistance = collectionUrl as ArrayList<Int>
                                setSuccessLoadData()
                                setLatestList(response.body(), pagePosition)
                            }
                        }



                    }else{
                        Log.d("sukses","tak")
                    }
                }


                override fun onFailure(call: Call<ModelMovie>, throwable: Throwable) {
                    Log.d("sukses","gagal")

                }
            })
        }

    }

    private fun setFromFavourite(collection: ArrayList<ModelItemMovie>) {
        viewLayer?.showCollectionMovie(collection)
    }

    private fun setErrorStatus(errorDataEmpty: Long) {
        viewLayer?.showFailedLoadData(errorDataEmpty)
    }

    private fun setLatestList(body: ModelMovie?, pagePosition: Int) {
        checkNotNull(viewLayer) {
            return
        }
        checkNotNull(body) {
            viewLayer?.showFailedLoadData(if (pagePosition > 1) TypeStatus.ERROR_DATA_COMPLETE else TypeStatus.ERROR_DATA_EMPTY)
            return
        }

        checkNotNull(body.results?.firstOrNull()) {
            viewLayer?.showFailedLoadData(if (pagePosition > 1) TypeStatus.ERROR_DATA_COMPLETE else TypeStatus.ERROR_DATA_EMPTY)
            return
        }
        val collectionContentList = ArrayList<ModelItemMovie>()
        body.results?.let { collectionContentList.addAll(it) }
        viewLayer?.showCollectionMovie(setFavouriteContent(collectionContentList))

    }

    private fun setFavouriteContent(collectionContent: ArrayList<ModelItemMovie>): ArrayList<ModelItemMovie> {
        collectionContent.filterIsInstance<ModelItemMovie>().forEach { it.hasFavourite = collectionPersistance.contains(it.id) }
        return collectionContent
    }

    private fun setSuccessLoadData() {
        viewLayer?.showSuccessLoadData()
    }

    private fun setLoadingProgress() {
        viewLayer?.showLoadingProcess()
    }

    fun contentFavourite(content: ModelItemMovie) {
        if (content.hasFavourite == true) {
            doAsync { repositoryContent?.contentDao()?.deleteFavourite(content.id) }
        } else {
            doAsync {
                val collectionId = repositoryContent?.contentDao()?.allContentIdFavourite(true)
                uiThread {
                    collectionPersistance.clear()
                    collectionPersistance = collectionId as ArrayList<Int>
                    if(collectionPersistance.contains(content.id)){

                    }else{
                        doAsync { repositoryContent?.contentDao()?.addContent(RepositoryContent.ContentConverterFavourite(content).toContent()!!) }

                    }

                }
            }
        }
    }


}