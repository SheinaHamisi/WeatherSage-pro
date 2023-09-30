package com.sheinahamisi.weathersagepro.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheinahamisi.weathersagepro.domain.util.NetworkStatus
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme

@Composable
fun BaseScreen(
    loading: Boolean = false,
    networkStatus: NetworkStatus = NetworkStatus.Available,
    networkStatusMessage: String = "Network unavailable. Check your internet connection and try again",
    onDismissNetworkStatusDialog: () -> Unit = { },
    content: @Composable () -> Unit
) {
    val networkStatusDialogBackground = when (networkStatus) {
        NetworkStatus.Available -> {
            Color(0xFF2C4CCB)
        }

        NetworkStatus.Losing -> {
            Color(0xFFffdad6)
        }

        NetworkStatus.Lost -> {
            Color(0xFF2C4CCB)
        }

        NetworkStatus.Unavailable -> {
            Color(0xFF2C4CCB)
        }

        else -> Color(0xFF2C4CCB)
    }

    val networkStatusDialogTextColor = when (networkStatus) {
        NetworkStatus.Available -> {
            Color.White
        }

        NetworkStatus.Losing -> {
            Color(0XFF410002)
        }

        NetworkStatus.Lost -> {
            Color.White
        }

        NetworkStatus.Unavailable -> {
            Color.White
        }

        else -> Color.White
    }



    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            content()
            when (networkStatus) {
                NetworkStatus.Losing,
                NetworkStatus.Lost,
                NetworkStatus.Unavailable -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(12.dp)
                            .background(
                                color = networkStatusDialogBackground,
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(24.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = networkStatusMessage,
                                color = networkStatusDialogTextColor
                            )
                            TextButton(
                                modifier = Modifier.align(Alignment.End),
                                onClick = onDismissNetworkStatusDialog
                            ) {
                                Text(
                                    text = "Dismiss",
                                    color = networkStatusDialogTextColor
                                )
                            }
                        }
                    }
                }
                else -> Unit
            }

        }
        if (loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth(), color = Color(0xFF2C4CCB))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    WeathersageProTheme {
        BaseScreen(
            networkStatus = NetworkStatus.Idle,
            loading = true
        ) {

        }
    }
}