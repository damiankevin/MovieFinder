package id.com.android.moviedb.feature.presenterlayer

import android.os.Handler
import id.com.android.moviedb.feature.viewlayer.ViewSplash
import id.com.android.moviedb.repository.RepositorySettings
import id.com.android.moviedb.tools.UtilConstant
import javax.inject.Inject


class PresenterSplash : PresenterBase<ViewSplash> {

    private var splashRunnable: Runnable? = null
    private var splashHandler: Handler? = null

    @Inject
    constructor(repositorySettings: RepositorySettings) {
        splashHandler                       = android.os.Handler()
    }

    fun viewCreated() {
        createSplashRunnable()
    }


    fun viewPaused() {
        splashRunnable?.let { splashHandler?.removeCallbacks(it) }
    }

    fun createSplashRunnable() {
        splashRunnable?.let {
            splashHandler?.removeCallbacks(it)
            splashHandler?.postDelayed(it, UtilConstant.SPLASH_DURATION.toLong())
        }
        viewLayer?.showLogoAnimation()
    }

}
