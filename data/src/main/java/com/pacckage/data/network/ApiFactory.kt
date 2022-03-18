package com.pacckage.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor().provideLoggingInterceptor())
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService = retrofit.create(ApiInterface::class.java)
}