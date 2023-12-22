package com.sheinahamisi.weathersagepro.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheinahamisi.weathersagepro.presentation.ui.theme.LocalFonts
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme
import com.sheinahamisi.weathersagepro.R

@Composable
fun LowerSection(
    modifier: Modifier = Modifier,
    temp: String,
    wind: String,
    dewPoint: String,
    humidity: String
) {
    BoxWithConstraints {
        val weatherDetailWidth = (maxWidth.value / 2).dp
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 46.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Weather now",
                fontFamily = LocalFonts.current.hanuman,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(17.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                WeatherDetail(modifier = Modifier.width(weatherDetailWidth), title = "Temperature", icon = R.drawable.thermometer, value = temp)
                WeatherDetail(Modifier.width(weatherDetailWidth),title = "Wind", icon = R.drawable.wind, value = wind)
            }
            Spacer(modifier = Modifier.height(34.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                WeatherDetail(Modifier.width(weatherDetailWidth),title = "Dew point", icon = R.drawable.umbrella, value = dewPoint)
                WeatherDetail(Modifier.width(weatherDetailWidth),title = "Humidity", icon = R.drawable.droplet, value = humidity)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LowerSectionPreview() {
    WeathersageProTheme {
        LowerSection(temp = "16°", wind = "24 km/h", dewPoint = "79%", humidity = "96%")
    }
}