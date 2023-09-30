package com.sheinahamisi.weathersagepro.domain.repository

import com.sheinahamisi.weathersagepro.core.util.Resource
import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse
import com.sheinahamisi.weathersagepro.domain.util.CurrentLocationResult
import com.sheinahamisi.weathersagepro.domain.util.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<OpenWeatherResponse>>
    suspend fun getCurrentLocation(): Flow<CurrentLocationResult>
    suspend fun getNetworkStatus(): Flow<NetworkStatus>
}