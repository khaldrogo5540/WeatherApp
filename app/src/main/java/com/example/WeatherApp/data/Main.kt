package com.example.WeatherApp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    val temp: Double,
    val feels_like: Double
):Parcelable