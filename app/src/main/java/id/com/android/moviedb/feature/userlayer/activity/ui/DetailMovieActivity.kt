package id.com.android.moviedb.feature.userlayer.activity.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import id.com.android.mov.PresenterDetail
import id.com.android.moviedb.R
import id.com.android.moviedb.feature.ActivityBase
import id.com.android.moviedb.feature.viewlayer.ViewDetail
import id.com.android.moviedb.model.ModelMovieDetail
import id.com.android.moviedb.tools.UtilConstant
import id.com.android.moviedb.tools.UtilImage
import kotlinx.android.synthetic.main.activity_detail_movie.*
import javax.inject.Inject


class DetailMovieActivity : ActivityBase(),ViewDetail {
    @Inject
    lateinit var presenterDetail: PresenterDetail
    var idMovie : Int = 0
    var hasFavourite : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        componentActivity?.inject(this)
        presenterDetail.attachView(this)
        initializeToolbar()
        idMovie = intent.getIntExtra(UtilConstant.ID_MOVIE, 0)
        presenterDetail.loadDetail(idMovie)
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }

        }
        return super.onOptionsItemSelected(item)
    }


    override fun showFailedLoadData(status: Long) {

    }

    override fun showDetailMovie(body: ModelMovieDetail?) {
        view_detail_budget.text = body?.budget.toString()
        view_detail_desc.text = body?.overview
        view_detail_duration.text = body?.runtime.toString() +" minutes"
        view_detail_release_date.text = body?.release_date
        view_detail_title.text = body?.original_title

        UtilImage.loadImageWithoutPlaceholder(view_movie_image,  UtilConstant.BASE_URL_IMAGE_SMALL+body?.poster_path, this)

        val itemGenre: ArrayList<String> = ArrayList()
        for(i in 0 until body?.genres!!.size){
            itemGenre.add(body?.genres!![i].name.toString())
        }

        val itemProduction: ArrayList<String> = ArrayList()
        for(i in 0 until body?.production_companies!!.size){
            itemProduction.add(body?.production_companies!![i].name.toString())
        }

        val genre: String = TextUtils.join(",", itemGenre)
        val production : String = TextUtils.join(",", itemProduction)
        view_detail_genre.text = genre
        view_detail_production.text = production

        view_detail_url.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(body.homepage))
            startActivity(browserIntent)
        }
        view_detail_rating.text = body?.vote_average.toString()
        if(body?.adult!!){
            view_detail_rating_age.text = "18++"
        }else{
            view_detail_rating_age.text = "R13"
        }
    }

    override fun showFavourite(hasFavourites: Boolean, movie: ModelMovieDetail?) {
            hasFavourite = hasFavourites
            view_detail_favourite?.setImageResource(if (hasFavourite) R.drawable.ic_heart_red else R.drawable.ic_heart)
            view_detail_favourite?.setOnClickListener {
                if(hasFavourite){
                    hasFavourite = false
                    view_detail_favourite?.setImageResource(R.drawable.ic_heart)
                    presenterDetail.contentFavourite(movie,false,idMovie)
                }else{
                    hasFavourite = true
                    view_detail_favourite?.setImageResource(R.drawable.ic_heart_red)
                    presenterDetail.contentFavourite(movie,true,idMovie)
                }
            }
    }
}