package com.vadim.weatherforecastapp.repository

import android.util.Log
import com.vadim.weatherforecastapp.data.DataOrException
import com.vadim.weatherforecastapp.model.Weather
import com.vadim.weatherforecastapp.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI){
    suspend fun getWeather(cityQuery: String, units: String):DataOrException<Weather, Boolean, Exception>{
        val response = try {
            api.getWeather(query = cityQuery, units = units)
        }catch (e: Exception){
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }
}