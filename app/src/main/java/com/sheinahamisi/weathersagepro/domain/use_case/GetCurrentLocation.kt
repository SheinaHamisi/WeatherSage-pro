package com.sheinahamisi.weathersagepro.domain.use_case

import com.sheinahamisi.weathersagepro.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentLocation @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke() = weatherRepository.getCurrentLocation()
}