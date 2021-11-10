package com.example.WeatherApp.di

import com.example.WeatherApp.data.APIService
import com.example.WeatherApp.data.ApiClient
import com.example.WeatherApp.domain.FetchWeatherUseCase
import com.example.WeatherApp.repository.ApiRepositoryImp

object DI {

    private fun provideService() =
        ApiClient.getRetrofit().create(APIService::class.java)

    private fun provideRepository() =
        ApiRepositoryImp(provideService())

    private fun provideUseCase() =
        FetchWeatherUseCase(provideRepository())

    val useCase by lazy {
        provideUseCase()
    }
}