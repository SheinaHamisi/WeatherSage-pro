package com.sheinahamisi.weathersagepro.presentation.components

import android.widget.DigitalClock
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.sheinahamisi.weathersagepro.R
import com.sheinahamisi.weathersagepro.presentation.ui.theme.LocalFonts
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme
import com.sheinahamisi.weathersagepro.presentation.util.parseDateText
import timber.log.Timber
import java.time.LocalDate

@Composable
fun WeatherSection(
    modifier: Modifier = Modifier,
    location: String,
    image: String,
    temperature: Double,
    weather: String,
    loading: Boolean
) {
    val hanuman = LocalFonts.current.hanuman

    SideEffect {
        Timber.tag("Screen").d("image: $image")
    }

    BoxWithConstraints(
        modifier = modifier
    ) {
        val height = maxHeight
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.upper_section_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 17.dp, vertical = 37.dp)
        ) {
            Text(
                text = location,
                fontFamily = hanuman,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(11.dp))
            Row {
                Text(
                    text = parseDateText(date = LocalDate.now()),
                    fontFamily = hanuman,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                AndroidView(
                    factory = {
                        DigitalClock(it).apply {
                            this.setTextColor(android.graphics.Color.WHITE)
                        }
                    },
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(33.dp))
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        if (loading) {
                            CircularProgressIndicator()
                        } else {
                            AsyncImage(
                                modifier = Modifier.size(150.dp),
                                model = image,
                                contentDescription = null,
                                placeholder = painterResource(id = R.drawable.cloud),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            Text(
                text = "${
                    if (temperature == 0.0) {
                        0
                    } else {
                        temperature.toString().substring(0, 2)
                    }
                }°",
                fontFamily = hanuman,
                fontWeight = FontWeight.Bold,
                fontSize = 76.sp,
                color = Color.White
            )
            Text(
                text = weather.replaceFirstChar { it.uppercase() },
                fontFamily = hanuman,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun WeatherSectionPreview() {
    WeathersageProTheme {
        WeatherSection(
            location = "Nairobi, Kenya",
            image = "",
            temperature = 16.2,
            weather = "Rainy",
            loading = false
        )
    }
}