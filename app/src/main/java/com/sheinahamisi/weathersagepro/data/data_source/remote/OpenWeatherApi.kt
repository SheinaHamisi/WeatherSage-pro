package com.sheinahamisi.weathersagepro.data.data_source.remote

import com.sheinahamisi.weathersagepro.data.dto.OpenWeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET(ApiEndpoints.CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "minutely,hourly",
        @Query("units") units: String = "metric",
    ): OpenWeatherResponseDto

}