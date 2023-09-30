package com.sheinahamisi.weathersagepro.core.util

sealed class Resource<T>(val data: T?, val message: UiText?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: UiText) : Resource<T>(null, message)
    class Loading<T> : Resource<T>(null, null)
}
