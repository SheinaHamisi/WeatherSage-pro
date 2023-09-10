package com.sheinahamisi.weathersagepro.data.repository

import com.sheinahamisi.weathersagepro.data.mappers.toOpenWeatherResponse
import com.sheinahamisi.weathersagepro.data.remote.OpenWeatherApi
import com.sheinahamisi.weathersagepro.data.util.Resource
import com.sheinahamisi.weathersagepro.data.util.safeApiCall
import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse
import com.sheinahamisi.weathersagepro.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val openWeatherApi: OpenWeatherApi
) : WeatherRepository {
    override suspend fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<OpenWeatherResponse>> {
        return safeApiCall { openWeatherApi.getCurrentWeather(lat, lon).toOpenWeatherResponse() }
    }
}