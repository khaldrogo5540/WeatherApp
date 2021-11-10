package com.example.WeatherApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.WeatherApp.R
import com.example.WeatherApp.data.Forecast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.WeatherApp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ForecastFragment.OpenDetailForecast {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLookupCity.setOnClickListener { processClick(binding.etCity.text.toString()) }

    }

    private fun processClick(cityInput: String) {
        if (cityInput.isNotEmpty())
            inflateForecastFragment(cityInput)
        else
            showError()
    }

    private fun showError() {
        MaterialAlertDialogBuilder(this).setTitle(getString(R.string.error_empty)).show()
    }

    private fun inflateForecastFragment(cityInput: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ForecastFragment.newInstance(cityInput))
            .addToBackStack(null)
            .commit()

    }

    override fun openDetails(dataItem: Forecast) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WeatherDetailFragment.newInstance(dataItem))
            .addToBackStack(null)
            .commit()
    }
}