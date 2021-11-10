package com.example.WeatherApp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API REST call using Retrofit
 */
interface APIService {
    @GET("data/2.5/forecast")
    suspend fun fetchData(
        @Query("q") q: String,
        @Query("appid") appid: String = "65d00499677e59496ca2f318eb68c049"
    ): Response<WeatherResponse>
}