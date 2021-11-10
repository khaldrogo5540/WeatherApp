package com.example.WeatherApp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.WeatherApp.data.WeatherResponse
import com.example.WeatherApp.domain.APIRepository
import com.example.WeatherApp.domain.FetchWeatherUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: FetchWeatherUseCase
): ViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse>
        get() = _weatherResponse

    fun getCityData(cityInput: String) {
        viewModelScope.launch {
            weatherUseCase.execute(cityInput).collect { data ->
                _weatherResponse.postValue(data)
            }
        }
    }
}