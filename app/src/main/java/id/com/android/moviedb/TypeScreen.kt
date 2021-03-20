package id.com.android.moviedb

import androidx.annotation.LongDef
import androidx.annotation.StringDef

object TypeScreen {

    const val POPULAR: String = "popular"
    const val UPCOMING: String  = "upcoming"
    const val TOP_RATED: String = "top_rated"
    const val NOW_PLAYING: String = "now_playing"
    const val FAVOURITE: String = "favourite"

    @StringDef(POPULAR, UPCOMING, TOP_RATED, NOW_PLAYING, FAVOURITE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class Screen
}
