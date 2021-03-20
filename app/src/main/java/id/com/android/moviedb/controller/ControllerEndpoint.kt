package id.com.android.moviedb.controller

import android.graphics.ColorSpace
import id.com.android.moviedb.model.ModelMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ControllerEndpoint {
    @GET("3/movie/popular")
    fun getPopular(
        @Query("page") page: Int?,
        @Query("api_key") api_key: String?
    ): Call<ModelMovie>

    @GET("3/movie/{section}")
    fun getUpcoming(
        @Path("section") section : String,
        @Query("page") page: Int?,
        @Query("api_key") api_key: String?

    ): Call<ModelMovie>

    @GET("3/movie/top_rated")
    fun getTopRated(
        @Query("page") page: Int?,
        @Query("api_key") api_key: String?
    ): Call<ModelMovie>

    @GET("3/movie/now_playing")
    fun getNowPlaying(
        @Query("page") page: Int?,
        @Query("api_key") api_key: String?
    ): Call<ModelMovie>
}