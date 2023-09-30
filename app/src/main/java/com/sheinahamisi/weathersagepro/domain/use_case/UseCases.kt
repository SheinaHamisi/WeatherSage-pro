package com.sheinahamisi.weathersagepro.domain.use_case

data class UseCases(
    val getCurrentWeather: GetCurrentWeather,
    val getCurrentLocation: GetCurrentLocation,
    val getNetworkStatus: GetNetworkStatus
)
