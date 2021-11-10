package com.example.WeatherApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.fragment.app.Fragment
import com.example.WeatherApp.data.Forecast
import com.example.WeatherApp.databinding.WeatherDetailPageBinding

class WeatherDetailFragment: Fragment(){

    companion object{
        const val EXTRA_FORECAST = "EXTRA_FORECAST"
        fun newInstance(weatherForecast: Forecast) =
            WeatherDetailFragment().apply{
                arguments= Bundle().apply{
                    putParcelable(EXTRA_FORECAST, weatherForecast)
                }
            }
    }

    private lateinit var binding: WeatherDetailPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherDetailPageBinding.inflate(inflater, container, false)
        arguments?.getParcelable<Forecast>(EXTRA_FORECAST)?.let { updateData(it) }
        return binding.root
    }

    private fun updateData(dataItem: Forecast) {
        binding.status.text = dataItem.weather[0].main
        binding.temp.text = dataItem.main.temp.toString() + "°F"
        binding.feelsLike.text = "Feels like: " + dataItem.main.feels_like.toString() +"°F"
        binding.weatherDescription.text = dataItem.weather[0].description
    }
}