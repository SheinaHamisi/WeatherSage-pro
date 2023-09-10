package com.sheinahamisi.weathersagepro.presentation.weather_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheinahamisi.weathersagepro.data.util.Resource
import com.sheinahamisi.weathersagepro.domain.use_case.UseCases
import com.sheinahamisi.weathersagepro.presentation.util.UiEvent
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
        viewModelScope.launch {
            _uiEvent.send(UiEvent.OnLaunch)
        }
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
                    showErrorDialog = false
                )
            }
            WeatherEvent.OnDragBottomSheet -> {

            }
        }
    }

    fun getCurrentWeather(
        lat: Double,
        lon: Double
    ) {
        viewModelScope.launch {
            useCases.getCurrentWeather(lat, lon).onEach { resource ->
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
                            showErrorDialog = true,
                            errorMessage = resource.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            loading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}