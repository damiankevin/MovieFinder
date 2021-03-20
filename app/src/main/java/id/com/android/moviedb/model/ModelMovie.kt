package id.com.android.moviedb.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class ModelMovie(
    @SerializedName("page")    var page: Int?,
    @SerializedName("total_pages") var total_pages: Int?,
    @SerializedName("total_results") var total_results: Int?,
    @SerializedName("results") var results: ArrayList<ModelItemMovie>?) : Parcelable