package com.sheinahamisi.weathersagepro.domain.model

data class Current(
    val dewPoint: Double,
    val dt: Int,
    val temp: Double,
    val humidity: Int,
    val title: String,
    val icon: String,
    val description: String,
    val windSpeed: Double
)
