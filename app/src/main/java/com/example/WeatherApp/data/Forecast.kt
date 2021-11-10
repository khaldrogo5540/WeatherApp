package com.example.WeatherApp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.collections.List

@Parcelize
data class Forecast(
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds
): Parcelable