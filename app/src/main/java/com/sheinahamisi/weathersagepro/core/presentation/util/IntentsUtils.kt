package com.sheinahamisi.weathersagepro.core.presentation.util

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


fun navigateToSettings(context: Context) {
    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
        context.startActivity(this)
    }
}