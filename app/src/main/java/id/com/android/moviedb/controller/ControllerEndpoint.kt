package id.com.android.moviedb.controller

import android.graphics.ColorSpace
import id.com.android.moviedb.model.ModelMovie
import id.com.android.moviedb.model.ModelMovieDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ControllerEndpoint {

    @GET("3/movie/{section}")
    suspend fun getListMovie(
        @Path("section") section : String,
        @Query("page") page: Int?,
        @Query("api_key") api_key: String?

    ): Response<ModelMovie>

    @GET("3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id : String,
        @Query("api_key") api_key: String?
    ): Response<ModelMovieDetail>

}