package id.com.android.moviedb

import id.com.android.moviedb.controller.ControllerEndpoint
import id.com.android.moviedb.feature.presenterlayer.PresenterMain
import id.com.android.moviedb.feature.viewlayer.ViewMain
import id.com.android.moviedb.repository.RepositoryContent
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class TestMain {

    @InjectMocks
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()
    private var presenter: PresenterMain? = null
    @Mock val view : ViewMain? = null
    @Mock private var serviceApi : ControllerEndpoint? = null
    @Mock private var repositoryContent: RepositoryContent? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = PresenterMain(serviceApi!!, repositoryContent!!)
        presenter!!.attachView(view!!)
    }

    @Test
    fun loadPopular(){
        presenter?.viewCreated("popular")
        verify(view)?.showSuccessLoadData()
    }

    @Test
    fun loadUpcoming(){
        presenter?.viewCreated("upcoming")
        verify(view)?.showSuccessLoadData()
    }

    @Test
    fun loadTopRated(){
        presenter?.viewCreated("top_rated")
        verify(view)?.showSuccessLoadData()
    }

    @Test
    fun loadNowPlaying(){
        presenter?.viewCreated("now_playing")
        verify(view)?.showSuccessLoadData()
    }

    @Test
    fun loadFavourite(){
        presenter?.viewCreated("favourite")
        verify(view)?.showSuccessLoadData()
    }


}
