package com.sheinahamisi.weathersagepro.presentation.weather_screen

import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse

data class WeatherState(
    var loading: Boolean = false,
    var showErrorDialog: Boolean = false,
    var errorMessage: String = "",
    var weather: OpenWeatherResponse? = null
)
