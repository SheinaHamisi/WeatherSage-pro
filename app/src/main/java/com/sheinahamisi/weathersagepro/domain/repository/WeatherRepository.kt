package com.sheinahamisi.weathersagepro.domain.repository

import com.sheinahamisi.weathersagepro.data.util.Resource
import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<OpenWeatherResponse>>
}