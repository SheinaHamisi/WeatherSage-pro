package com.sheinahamisi.weathersagepro.domain.util

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
    object Losing : NetworkStatus()
    object Lost : NetworkStatus()
    object Idle : NetworkStatus()
}
