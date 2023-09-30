package com.sheinahamisi.weathersagepro.domain.use_case

import com.sheinahamisi.weathersagepro.domain.repository.WeatherRepository
import javax.inject.Inject

class GetNetworkStatus @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke() = repository.getNetworkStatus()

}