package com.sheinahamisi.weathersagepro.presentation.weather_screen

sealed class WeatherEvent {
    object OnClickShareWeatherIcon : WeatherEvent()
    object OnDragBottomSheet : WeatherEvent()
    object OnDismissErrorDialog : WeatherEvent()
    object ShowPermissionErrorDialog : WeatherEvent()
    object OnDismissShowPermissionErrorDialog : WeatherEvent()
    object OnDismissShowNetworkErrorDialog : WeatherEvent()
    object OnClickRetry : WeatherEvent()
    object OnDismissNetworkStatusDialog : WeatherEvent()
}
