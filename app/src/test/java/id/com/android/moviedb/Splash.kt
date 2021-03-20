package id.com.android.moviedb

import id.com.android.moviedb.feature.viewlayer.ViewSplash
import id.com.android.moviedb.repository.RepositorySettings
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
class Splash {
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()
    private var presenter: PresenterSplash? = null
    @Mock val view : ViewSplash? = null
    @Mock var repositorySettings: RepositorySettings? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PresenterSplash(
            this.repositorySettings!!
        )
    }

    @Test
    fun checkSession(){
        presenter?.createSplashRunnable()

        Mockito.verify(view)?.showLogoAnimation(true)
    }
}
