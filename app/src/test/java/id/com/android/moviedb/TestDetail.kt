package id.com.android.moviedb

import id.com.android.mov.PresenterDetail
import id.com.android.moviedb.controller.ControllerEndpoint
import id.com.android.moviedb.feature.presenterlayer.PresenterMain
import id.com.android.moviedb.feature.viewlayer.ViewDetail
import id.com.android.moviedb.feature.viewlayer.ViewMain
import id.com.android.moviedb.repository.RepositoryContent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


class TestDetail {

    @InjectMocks
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()
    private var presenter: PresenterDetail? = null
    @Mock
    val view : ViewDetail? = null
    @Mock
    private var serviceApi : ControllerEndpoint? = null
    @Mock
    private var repositoryContent: RepositoryContent? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = PresenterDetail(serviceApi!!, repositoryContent!!)
        presenter!!.attachView(view!!)
    }

    @Test
    fun loadDetail(){
        presenter?.loadDetail(1311301203)
        Mockito.verify(view)?.showFailedLoadData(TypeStatus.ERROR_DATA_EMPTY)
    }

    

}
