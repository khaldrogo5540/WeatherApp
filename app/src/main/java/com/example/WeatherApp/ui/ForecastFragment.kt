package com.example.WeatherApp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.WeatherApp.data.Forecast
import com.example.WeatherApp.data.WeatherResponse
import com.example.WeatherApp.databinding.WeatherListBinding
import com.example.WeatherApp.di.DI
import com.example.WeatherApp.presentation.WeatherViewModel
import com.example.WeatherApp.ui.adapter.WeatherAdapter

class ForecastFragment : Fragment() {

    interface OpenDetailForecast{
        fun openDetails(dataItem: Forecast)
    }

    companion object {
        const val EXTRA_CITY_NAME = "EXTRA_CITY_NAME"
        fun newInstance(cityName: String): ForecastFragment {
            val args = Bundle()
                .apply { putString(EXTRA_CITY_NAME, cityName) }
            val fragment = ForecastFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(viewModelStore,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return WeatherViewModel(DI.useCase) as T
                }
            })[WeatherViewModel::class.java]
    }

    private lateinit var binding: WeatherListBinding
    private lateinit var listener: OpenDetailForecast

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context){
            is OpenDetailForecast -> listener = context
            else -> throw ExceptionInInitializerError("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherListBinding.inflate(inflater, container, false)

        viewModel.weatherResponse.observe(viewLifecycleOwner){
            updateResponse(it)
        }
        arguments?.getString(EXTRA_CITY_NAME)?.let { viewModel.getCityData(it) }
        return binding.root
    }

    private fun updateResponse(response: WeatherResponse) {
        binding.forecastList.layoutManager = LinearLayoutManager(context)
        binding.forecastList.adapter = WeatherAdapter(response.list, ::openDetail)
    }

    private fun openDetail(dataItem: Forecast){
        listener.openDetails(dataItem)
    }
}