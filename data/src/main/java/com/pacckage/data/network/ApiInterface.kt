package com.pacckage.data.network

import com.pacckage.data.network.model.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieDto

}