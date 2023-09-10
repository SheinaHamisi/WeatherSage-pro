package com.sheinahamisi.weathersagepro.presentation.weather_screen

sealed class WeatherEvent {
    object OnClickShareWeatherIcon : WeatherEvent()
    object OnDragBottomSheet : WeatherEvent()
    object OnDismissErrorDialog : WeatherEvent()
}
