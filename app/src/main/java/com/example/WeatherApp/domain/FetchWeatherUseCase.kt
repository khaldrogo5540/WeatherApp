package com.example.WeatherApp.domain

import com.example.WeatherApp.data.WeatherResponse
import kotlinx.coroutines.flow.Flow

class FetchWeatherUseCase(
    private val repository: APIRepository
) {
    suspend fun execute(cityInput: String): Flow<WeatherResponse> {
        return repository.fetchData(cityInput)
    }
}