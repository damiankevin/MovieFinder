package id.com.android.moviedb.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ModelMovieDetail(
    @SerializedName("adult")    var adult: Boolean?,
    @SerializedName("backdrop_path") var backdrop_path: String?,
    @SerializedName("budget") var budget: Int?,
    @SerializedName("genres") var genres: ArrayList<Genre>?,
    @SerializedName("homepage") var homepage: String?,
    @SerializedName("original_title") var original_title: String?,
    @SerializedName("overview") var overview: String?,
    @SerializedName("poster_path") var poster_path: String?,
    @SerializedName("release_date") var release_date: String?,
    @SerializedName("revenue") var revenue: Int?,
    @SerializedName("runtime") var runtime: Int?,
    @SerializedName("vote_average") var vote_average: Double?,
    @SerializedName("tagline") var tagline: String?,
    @SerializedName("production_companies") var production_companies: ArrayList<ProductionCompany>?
) : Parcelable