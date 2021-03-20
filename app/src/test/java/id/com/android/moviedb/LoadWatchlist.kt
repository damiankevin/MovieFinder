package id.com.android.moviedb

import id.com.android.moviedb.controller.ControllerEndpoint
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoadWatchlist {
    @JvmField @Rule var mockitoRule = MockitoJUnit.rule()
    private var presenter: PresenterWatchlist? = null
    @Mock val view : ViewWatchlist? = null
    @Mock var controllerEndpoint: ControllerEndpoint? = null
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PresenterWatchlist(
            this.controllerEndpoint!!
        )
    }

    @Test
    fun loadWatchlist(){
        presenter?.pageLoaded(1)
        Mockito.verify(view)?.showSuccessLoadData()
    }
}
