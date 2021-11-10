package com.example.WeatherApp.domain

import com.example.WeatherApp.data.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface APIRepository {
    suspend fun fetchData(city: String): Flow<WeatherResponse>
}