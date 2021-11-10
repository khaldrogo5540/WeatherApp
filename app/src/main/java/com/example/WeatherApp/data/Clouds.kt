package com.example.WeatherApp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clouds (
    val all: Int
    ): Parcelable
