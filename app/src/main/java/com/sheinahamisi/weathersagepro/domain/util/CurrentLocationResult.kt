package com.sheinahamisi.weathersagepro.domain.util

sealed class CurrentLocationResult {
    data class Success(val lat: Double, val lon: Double) : CurrentLocationResult()
    object Error : CurrentLocationResult()
    object RequestPermission : CurrentLocationResult()
    object Retry : CurrentLocationResult()
}
