package com.sheinahamisi.weathersagepro.presentation.weather_screen

import com.sheinahamisi.weathersagepro.R
import com.sheinahamisi.weathersagepro.core.util.UiText
import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse
import com.sheinahamisi.weathersagepro.domain.util.NetworkStatus

data class WeatherState(
    val loading: Boolean = false,
    val showPermissionErrorDialog: Boolean = false,
    val permissionErrorMessage: UiText? = null,
    val showLocationErrorDialog: Boolean = false,
    val locationErrorMessage: UiText? = null,
    val showNetworkErrorDialog: Boolean = false,
    val networkErrorMessage: UiText? = null,
    val weather: OpenWeatherResponse? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val isLocationError: Boolean= false,
    val networkStatusErrorMessage: UiText = UiText.StringResource(R.string.network_unavailable),
    val networkStatus: NetworkStatus = NetworkStatus.Idle
)
