package id.com.android.moviedb.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

@Entity(tableName = "content")
data class ModelItemMovie(
    @SerializedName("adult") var adult: Boolean?,
    @SerializedName("backdrop_path") var backdrop_path: String?,
    @PrimaryKey @SerializedName("id") var id: Int?,
    @SerializedName("original_language") var original_language: String?,
    @SerializedName("original_title") var original_title: String?,
    @SerializedName("overview") var overview: String?,
    @SerializedName("popularity") var popularity: Double?,
    @SerializedName("poster_path") var poster_path: String?,
    @SerializedName("release_date") var release_date: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("video") var video: Boolean?,
    @SerializedName("vote_average") var vote_average: Double?,
    @SerializedName("vote_count") var vote_count: Int?,
    @SerializedName("favourite") var hasFavourite: Boolean?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readValue(Boolean::class.java.classLoader) as Boolean,
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean,
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Boolean::class.java.classLoader) as Boolean
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(adult)
        writeString(backdrop_path)
        writeValue(id)
        writeString(original_language)
        writeString(original_title)
        writeString(overview)
        writeValue(popularity)
        writeString(poster_path)
        writeString(release_date)
        writeString(title)
        writeValue(vote_average)
        writeValue(vote_count)
        writeValue(hasFavourite)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelItemMovie> = object : Parcelable.Creator<ModelItemMovie> {
            override fun createFromParcel(source: Parcel): ModelItemMovie = ModelItemMovie(source)
            override fun newArray(size: Int): Array<ModelItemMovie?> = arrayOfNulls(size)
        }
    }
}