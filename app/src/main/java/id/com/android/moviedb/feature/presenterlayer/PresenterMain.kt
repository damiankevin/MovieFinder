package id.com.android.moviedb.feature.presenterlayer

import android.util.Log
import id.com.android.moviedb.controller.ControllerEndpoint
import id.com.android.moviedb.feature.viewlayer.ViewMain
import id.com.android.moviedb.tools.UtilConstant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PresenterMain : PresenterBase<ViewMain> {

    private var serviceApi : ControllerEndpoint

    @Inject
    constructor(controllerServiceApi: ControllerEndpoint) {
        this.serviceApi  = controllerServiceApi
    }


    private fun setErrorStatus() {
        viewLayer?.showFailedLoadData()
    }

    private fun setLatestList() {
    }

    private fun setSuccessLoadData() {
        viewLayer?.showSuccessLoadData()
    }

    private fun setLoadingProgress() {
        viewLayer?.showLoadingProcess()
    }



}