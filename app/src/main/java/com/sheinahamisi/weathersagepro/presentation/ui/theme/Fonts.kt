package com.sheinahamisi.weathersagepro.presentation.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sheinahamisi.weathersagepro.R

data class Fonts(
    val hanuman: FontFamily = FontFamily(
        listOf(
            Font(
                resId = R.font.hanuman_black,
                weight = FontWeight.Black
            ),
            Font(
                resId = R.font.hanuman_bold,
                weight = FontWeight.Bold
            ),
            Font(
                resId = R.font.hanuman_light,
                weight = FontWeight.Light
            ),
            Font(
                resId = R.font.hanuman_regular,
                weight = FontWeight.Normal
            ),
            Font(
                resId = R.font.hanuman_thin,
                weight = FontWeight.Thin
            )
        )
    )
)

val LocalFonts = compositionLocalOf {
    Fonts()
}