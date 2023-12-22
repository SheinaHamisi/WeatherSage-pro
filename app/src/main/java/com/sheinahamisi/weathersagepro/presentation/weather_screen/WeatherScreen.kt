package com.sheinahamisi.weathersagepro.presentation.weather_screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme
import com.sheinahamisi.weathersagepro.core.presentation.util.UiEvent
import com.sheinahamisi.weathersagepro.core.presentation.util.navigateToSettings
import com.sheinahamisi.weathersagepro.presentation.components.BaseScreen
import com.sheinahamisi.weathersagepro.presentation.components.ErrorDialog
import com.sheinahamisi.weathersagepro.presentation.components.LowerSection
import com.sheinahamisi.weathersagepro.presentation.components.WeatherSection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    WeatherScreenContent(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        uiEvent = viewModel.uiEvent,
        getLocationData = viewModel::getCurrentLocation
    )
}

@Composable
fun WeatherScreenContent(
    state: WeatherState,
    onEvent: (WeatherEvent) -> Unit,
    uiEvent: Flow<UiEvent>,
    getLocationData: () -> Unit,

    ) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { acceptedPermissions ->
            if (!acceptedPermissions.values.any()) {
                onEvent(WeatherEvent.ShowPermissionErrorDialog)
            } else {
                getLocationData()
            }
        }
    )

    LaunchedEffect(key1 = true) {
        uiEvent.collect { uiEvent ->
            when (uiEvent) {
                UiEvent.RequestForPermission -> {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    )
                }

                else -> Unit
            }
        }
    }

    ErrorDialog(
        showDialog = state.showPermissionErrorDialog,
        onDismiss = { onEvent(WeatherEvent.OnDismissShowPermissionErrorDialog) },
        text = "Unable to request for location permissions. Enable your location settings to get the current weather updates.",
        action = {
            navigateToSettings(context)
        },
        actionText = "Go to settings"
    )

    ErrorDialog(
        showDialog = state.showNetworkErrorDialog,
        onDismiss = { onEvent(WeatherEvent.OnDismissShowNetworkErrorDialog) },
        text = state.networkErrorMessage?.asString(context) ?: "",
        action = { onEvent(WeatherEvent.OnClickRetry) },
        actionText = "Retry"
    )

    BaseScreen(
        loading = state.loading,
        networkStatus = state.networkStatus,
        networkStatusMessage = state.networkStatusErrorMessage.asString(context),
        onDismissNetworkStatusDialog = { onEvent(WeatherEvent.OnDismissNetworkStatusDialog) }
    ) {
        BoxWithConstraints {
            val upperSectionHeight = (maxHeight.value * 0.65).dp
            Column(modifier = Modifier.fillMaxSize()) {
                WeatherSection(
                    modifier = Modifier.height(upperSectionHeight),
                    location = state.weather?.timeZone ?: "",
                    image = state.weather?.current?.icon ?: "",
                    temperature = state.weather?.current?.temp ?: 0.0,
                    weather = state.weather?.current?.description ?: "",
                    loading = state.loading
                )
                LowerSection(
                    modifier = Modifier.weight(1f),
                    temp = "${state.weather?.current?.temp ?: 0}",
                    wind = "${state.weather?.current?.windSpeed ?: 0}",
                    dewPoint = "${state.weather?.current?.dewPoint ?: 0}",
                    humidity = "${state.weather?.current?.humidity ?: 0}"
                )
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
            onEvent = { },
            uiEvent = flowOf(),
            getLocationData = { }
        )
    }
}