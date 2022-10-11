package com.vadim.weatherforecastapp.network

import com.vadim.weatherforecastapp.model.Weather
import com.vadim.weatherforecastapp.model.WeatherObject
import com.vadim.weatherforecastapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY
    ): Weather
}