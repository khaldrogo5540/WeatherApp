package com.example.WeatherApp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.WeatherApp.data.Forecast
import com.example.WeatherApp.data.Weather
import com.example.WeatherApp.databinding.WeatherItemBinding

class WeatherAdapter(
    private val dataSet: List<Forecast>,
    private val clickListener: (Forecast) -> Unit
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dataItem: Forecast, clickDetails: (Forecast) -> Unit) {
            binding.condition.text = dataItem.weather[0].description
            binding.temperature.text = dataItem.main.feels_like.toString()
            binding.root.setOnClickListener {
                clickDetails(dataItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(dataSet[position], clickListener)
    }

    override fun getItemCount() = dataSet.size
}