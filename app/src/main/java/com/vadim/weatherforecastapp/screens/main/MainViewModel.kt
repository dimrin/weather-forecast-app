package com.vadim.weatherforecastapp.screens.main

import androidx.lifecycle.ViewModel
import com.vadim.weatherforecastapp.data.DataOrException
import com.vadim.weatherforecastapp.model.Weather
import com.vadim.weatherforecastapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    suspend fun getWeatherData(city: String, units: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)
    }



}