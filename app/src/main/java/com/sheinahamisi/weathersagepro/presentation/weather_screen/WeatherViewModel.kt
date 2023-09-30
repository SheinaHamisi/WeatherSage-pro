package com.sheinahamisi.weathersagepro.presentation.weather_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheinahamisi.weathersagepro.R
import com.sheinahamisi.weathersagepro.core.util.Resource
import com.sheinahamisi.weathersagepro.domain.use_case.UseCases
import com.sheinahamisi.weathersagepro.domain.util.CurrentLocationResult
import com.sheinahamisi.weathersagepro.core.presentation.util.UiEvent
import com.sheinahamisi.weathersagepro.core.util.UiText
import com.sheinahamisi.weathersagepro.domain.util.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var state: WeatherState by mutableStateOf(WeatherState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getCurrentLocation()
        getCurrentNetworkStatus()
    }

    fun onEvent(event: WeatherEvent) {
        when (event) {
            WeatherEvent.OnClickShareWeatherIcon -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Share)
                }
            }

            WeatherEvent.OnDismissErrorDialog -> {
                state = state.copy(
                    showPermissionErrorDialog = false
                )
            }

            WeatherEvent.OnDragBottomSheet -> {

            }

            WeatherEvent.ShowPermissionErrorDialog -> {
                state = state.copy(
                    showPermissionErrorDialog = true
                )
            }

            WeatherEvent.OnDismissShowPermissionErrorDialog -> {
                state = state.copy(
                    showPermissionErrorDialog = false
                )
            }

            WeatherEvent.OnClickRetry -> {
                state = state.copy(
                    showNetworkErrorDialog = false
                )
                getCurrentLocation()
            }

            WeatherEvent.OnDismissShowNetworkErrorDialog -> {
                state = state.copy(
                    showNetworkErrorDialog = false
                )
            }

            WeatherEvent.OnDismissNetworkStatusDialog -> {
                state = state.copy(
                    networkStatus = NetworkStatus.Idle
                )
            }
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch {
            useCases.getCurrentLocation().onEach { result ->
                when (result) {
                    is CurrentLocationResult.Success -> {
                        state = state.copy(
                            lat = result.lat,
                            lon = result.lon,
                            showPermissionErrorDialog = false
                        )
                        Timber.tag("WEATHER_VIEW_MODEL").d(
                            """
                            location coordinates:
                            lat: ${result.lat}
                            lon: ${result.lon}
                        """.trimIndent()
                        )

                        getCurrentWeather()
                    }

                    CurrentLocationResult.Error -> {
                        Timber.tag("WEATHER_VIEW_MODEL").d(
                            """
                            CurrentLocationResult.Error
                        """.trimIndent()
                        )
                        state = state.copy(
                            showPermissionErrorDialog = true
                        )
                    }

                    CurrentLocationResult.RequestPermission -> {
                        _uiEvent.send(UiEvent.RequestForPermission)
                    }

                    CurrentLocationResult.Retry -> {
                        getCurrentWeather()
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getCurrentNetworkStatus() {
        viewModelScope.launch {
            useCases.getNetworkStatus().onEach { result ->
                when (result) {
                    NetworkStatus.Available -> {
                        state = state.copy(
                            showNetworkErrorDialog = false,
                            networkStatus = NetworkStatus.Available
                        )
                        getCurrentLocation()
                    }

                    NetworkStatus.Losing -> {
                        state = state.copy(
                            networkStatusErrorMessage = UiText.StringResource(
                                R.string.poor_network_connection
                            ),
                            networkStatus = NetworkStatus.Losing
                        )
                    }

                    NetworkStatus.Lost -> {
                        state = state.copy(
                            networkStatusErrorMessage = UiText.StringResource(
                                R.string.network_unavailable
                            ),
                            networkStatus = NetworkStatus.Lost
                        )
                    }

                    NetworkStatus.Unavailable -> {
                        state = state.copy(
                            networkStatusErrorMessage = UiText.StringResource(
                                R.string.network_unavailable
                            ),
                            networkStatus = NetworkStatus.Unavailable
                        )
                    }
                    NetworkStatus.Idle -> Unit
                }
            }.launchIn(this)
        }
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            if (state.lat != null && state.lon != null) {
                useCases.getCurrentWeather(state.lat!!, state.lon!!).onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            state = state.copy(
                                loading = false,
                                weather = resource.data
                            )
                            Timber.tag("WEATHER_TAG").d("Weather: ${resource.data}")
                        }

                        is Resource.Error -> {
                            state = state.copy(
                                loading = false,
                                showNetworkErrorDialog = true,
                                networkErrorMessage = resource.message
                            )
                        }

                        is Resource.Loading -> {
                            state = state.copy(
                                loading = true
                            )
                        }
                    }
                }.launchIn(this)
            } else {
                getCurrentLocation()
            }
        }
    }
}