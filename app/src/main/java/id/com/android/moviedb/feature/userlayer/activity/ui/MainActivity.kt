package id.com.android.moviedb.feature.userlayer.activity.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.navigation.NavigationView
import id.com.android.moviedb.R
import id.com.android.moviedb.TypeScreen
import id.com.android.moviedb.TypeStatus
import id.com.android.moviedb.adapter.AdapterCollectionMovie
import id.com.android.moviedb.controller.ControllerScroll
import id.com.android.moviedb.feature.ActivityBase
import id.com.android.moviedb.feature.interfacelayer.InterfaceContentCollection
import id.com.android.moviedb.feature.presenterlayer.PresenterMain
import id.com.android.moviedb.feature.viewlayer.ViewMain
import id.com.android.moviedb.model.ModelItemMovie
import id.com.android.moviedb.tools.UtilConstant
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : ActivityBase(), NavigationView.OnNavigationItemSelectedListener,
    ViewMain,
    ControllerScroll.PageListener,
    InterfaceContentCollection,
    SwipeRefreshLayout.OnRefreshListener{

    @Inject
    lateinit var presenterMain : PresenterMain
    var currentScreen: String = TypeScreen.POPULAR
    private lateinit var controllerScroll: ControllerScroll
    private lateinit var adapterCollectionMovie: AdapterCollectionMovie
    var pagesMain : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        componentActivity?.inject(this)
        presenterMain.attachView(this)
        initializeToolbar()
        initializeCollection()
        view_main_header_text.text =    currentScreen
        presenterMain.viewCreated(currentScreen)
    }

    private fun initializeCollection() {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        controllerScroll = ControllerScroll(layoutManager, this)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        adapterCollectionMovie = AdapterCollectionMovie(this)

        adapterCollectionMovie.interfaceContentCollection = this
        view_collection_swiperefreshlayout.setOnRefreshListener(this)
        view_collection_recyclerview?.layoutManager = layoutManager
        view_collection_recyclerview?.adapter = adapterCollectionMovie

        view_collection_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(currentScreen!=TypeScreen.FAVOURITE){
                        var pages = pagesMain++
                        presenterMain.pageLoaded(currentScreen, pages)
                    }
                }
            }
        })

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.title = ""
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()
        navigationView!!.setNavigationItemSelectedListener(this)
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_now_playing -> {
                currentScreen = TypeScreen.NOW_PLAYING
                onRefresh()
            }
            R.id.nav_upcoming -> {
                currentScreen = TypeScreen.UPCOMING
                onRefresh()
            }
            R.id.nav_top_rated -> {
                currentScreen = TypeScreen.TOP_RATED
                onRefresh()
            }
            R.id.nav_popular -> {
                currentScreen = TypeScreen.POPULAR
                onRefresh()
            }

            R.id.nav_favourite -> {
                currentScreen = TypeScreen.FAVOURITE
                onRefresh()
            }

        }
        view_main_header_text.text =    currentScreen
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showLoadingProcess() {
        view_collection_recyclerview?.post {
            adapterCollectionMovie.footerStatusCode = TypeStatus.LOADING_PROGRESS
            adapterCollectionMovie.notifyItemChanged(adapterCollectionMovie.itemCount.minus(1))
        }
    }

    override fun showFailedLoadData(status: Long) {
        view_collection_recyclerview?.post {
            adapterCollectionMovie.footerStatusCode = status
            adapterCollectionMovie.notifyItemChanged(adapterCollectionMovie.itemCount.minus(1))
        }
    }



    override fun showSuccessLoadData() {
        adapterCollectionMovie.footerStatusCode = TypeStatus.SUCCESS_LOAD
        adapterCollectionMovie.notifyItemChanged(adapterCollectionMovie.itemCount.minus(1))
    }

    override fun showCollectionMovie(collectionMovie: ArrayList<ModelItemMovie>) {
        adapterCollectionMovie.collectionMovie.addAll(collectionMovie)
        adapterCollectionMovie.notifyItemRangeInserted(
            adapterCollectionMovie.itemCount,
            adapterCollectionMovie.collectionMovie.size
        )
    }

    override fun onLoadMore(page: Int) {
    }

    override fun onContentFavourite(content: ModelItemMovie, position: Int) {
        presenterMain.contentFavourite(content)
        content.hasFavourite = true
        adapterCollectionMovie.notifyItemChanged(position)
        Toast.makeText(this, R.string.SIGN_SAVED_CONTENT, Toast.LENGTH_SHORT).show()
    }

    override fun onContentUnFavourite(content: ModelItemMovie, position: Int) {
        presenterMain.contentFavourite(content)
        content.hasFavourite = false
        adapterCollectionMovie.notifyItemChanged(position)
        Toast.makeText(this, R.string.SIGN_UNSAVED_CONTENT, Toast.LENGTH_SHORT).show()
    }

    override fun onContentSelected(content: ModelItemMovie) {
        val intent          = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra(UtilConstant.ID_MOVIE,content.id)
        startActivity(intent)
    }

    override fun onRefresh() {
        view_collection_swiperefreshlayout?.isRefreshing = false
        controllerScroll.resetLoadmore()
        adapterCollectionMovie.collectionMovie.clear()
        adapterCollectionMovie.notifyDataSetChanged()
        presenterMain.pageLoaded(currentScreen, UtilConstant.INITIAL_START_PAGE)
    }


}