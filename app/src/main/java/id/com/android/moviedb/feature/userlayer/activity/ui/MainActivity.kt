package id.com.android.moviedb.feature.userlayer.activity.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import id.com.android.moviedb.R
import id.com.android.moviedb.feature.ActivityBase
import id.com.android.moviedb.feature.presenterlayer.PresenterMain
import id.com.android.moviedb.feature.viewlayer.ViewMain
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : ActivityBase(), NavigationView.OnNavigationItemSelectedListener,
    ViewMain {

    private var pageScreenPool = WeakHashMap<Long, Fragment>()
    @Inject
    lateinit var presenterMain : PresenterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        componentActivity?.inject(this)
        presenterMain.attachView(this)
        initializeToolbar()

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

            }
            R.id.nav_upcoming -> {

            }
            R.id.nav_top_rated -> {

            }
            R.id.nav_popular -> {

            }

        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showLoadingProcess() {

    }

    override fun showFailedLoadData() {

    }

    override fun showSuccessLoadData() {

    }


}