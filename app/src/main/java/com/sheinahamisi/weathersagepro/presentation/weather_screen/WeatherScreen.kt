package com.sheinahamisi.weathersagepro.presentation.weather_screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme
import com.sheinahamisi.weathersagepro.core.presentation.util.UiEvent
import com.sheinahamisi.weathersagepro.core.presentation.util.navigateToSettings
import com.sheinahamisi.weathersagepro.presentation.components.BaseScreen
import com.sheinahamisi.weathersagepro.presentation.components.ErrorDialog
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