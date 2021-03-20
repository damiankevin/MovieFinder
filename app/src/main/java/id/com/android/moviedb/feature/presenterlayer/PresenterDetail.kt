package id.com.android.mov

import id.com.android.moviedb.feature.presenterlayer.PresenterBase
import id.com.android.moviedb.feature.viewlayer.ViewDetail

import id.com.android.moviedb.controller.ControllerEndpoint
import id.com.android.moviedb.model.ModelMovieDetail
import id.com.android.moviedb.repository.RepositoryContent
import id.com.android.moviedb.tools.UtilConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class PresenterDetail : PresenterBase<ViewDetail> {
    private var serviceApi : ControllerEndpoint
    private var repositoryContent: RepositoryContent?

    @Inject
    constructor(controllerServiceApi: ControllerEndpoint, repositoryContent: RepositoryContent) {
        this.serviceApi = controllerServiceApi
        this.repositoryContent = repositoryContent
    }

    fun loadDetail(id: Int) {
        GlobalScope.launch(Dispatchers.IO){
            val response = serviceApi.getMovieDetail(id.toString(),UtilConstant.API_KEY)
            if(response.isSuccessful){
                doAsync {
                    val collectionContent = repositoryContent?.contentDao()?.getContentById(id)
                    uiThread {

                        viewLayer?.showDetailMovie(response.body())
                        if(collectionContent?.size==0){
                            viewLayer?.showFavourite(false,response.body())
                        }else{
                            viewLayer?.showFavourite(true,response.body())
                        }
                    }
                }
            }else{

            }
        }

    }


    fun contentFavourite(content: ModelMovieDetail?, favourite: Boolean, idMovie: Int) {
        if(favourite){
            doAsync { repositoryContent?.contentDao()?.addContent(RepositoryContent.ContentDetailConverterFavourite(content,idMovie).toContent()!!) }

        }else{
            doAsync { repositoryContent?.contentDao()?.deleteFavourite(idMovie) }
        }

    }


}