package com.sheinahamisi.weathersagepro.presentation.weather_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sheinahamisi.weathersagepro.presentation.components.getUserLocation
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme
import com.sheinahamisi.weathersagepro.presentation.util.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    WeatherScreenContent(
        state = viewModel.state,
        event = viewModel::onEvent,
        uiEvent = viewModel.uiEvent,
        getCurrentWeatherData = { lat, lon ->
            Timber.tag("WeatherScreen").d("lat: $lat, lon: $lon")
            viewModel.getCurrentWeather(lat, lon)
        }
    )
}

@Composable
fun WeatherScreenContent(
    state: WeatherState,
    event: (WeatherEvent) -> Unit,
    uiEvent: Flow<UiEvent>,
    getCurrentWeatherData: (Double, Double) -> Unit
) {
    val context = LocalContext.current
    val userLatLong = getUserLocation(context)
    LaunchedEffect(key1 = true) {
        uiEvent.collect { uiEvent ->
            when (uiEvent) {
                UiEvent.OnLaunch -> {
                    Timber.tag("WeatherScreenContent").d("userLatLong: $userLatLong")
                    getCurrentWeatherData(userLatLong.latitude, userLatLong.longitude)
                }
                UiEvent.Share -> {

                }
                else -> Unit
            }
        }
    }
}

@Preview
@Composable
fun WeatherScreenContentPreview() {
    WeathersageProTheme {
        WeatherScreenContent(
            state = WeatherState(),
            event = { },
            uiEvent = flowOf(),
            getCurrentWeatherData = { _, _ ->}
        )
    }
}