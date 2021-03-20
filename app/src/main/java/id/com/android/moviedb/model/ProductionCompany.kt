package id.com.android.moviedb.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class ProductionCompany(
    @SerializedName("id")    var id: Int?,
    @SerializedName("logo_path") var logo_path: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("origin_country") var origin_country: String?
) : Parcelable