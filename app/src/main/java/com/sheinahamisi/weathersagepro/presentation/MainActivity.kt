package com.sheinahamisi.weathersagepro.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeathersageProTheme {

            }
        }
    }
}