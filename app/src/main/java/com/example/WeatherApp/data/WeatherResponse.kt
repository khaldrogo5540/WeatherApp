package com.example.WeatherApp.data

import kotlin.collections.List

data class WeatherResponse(
    val cod: String,
    val message: Int,
    val list: List<Forecast>,
    val city: City
)