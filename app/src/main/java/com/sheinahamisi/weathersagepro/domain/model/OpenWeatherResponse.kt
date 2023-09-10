package com.sheinahamisi.weathersagepro.domain.model

data class OpenWeatherResponse(
    val dailyWeatherList: List<Daily>,
    val timeZone: String,
    val lat: Double,
    val lon: Double,
    val current: Current
)
