package com.example.WeatherApp.repository

import com.example.WeatherApp.data.APIService
import com.example.WeatherApp.data.WeatherResponse
import com.example.WeatherApp.domain.APIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImp(private val service: APIService): APIRepository {

    override suspend fun fetchData(city: String): Flow<WeatherResponse> {
        return flow {
            val response = service.fetchData(
                city
            )
            if (response.isSuccessful && response.body() != null)
                emit(response.body()!!)
        }
    }
}