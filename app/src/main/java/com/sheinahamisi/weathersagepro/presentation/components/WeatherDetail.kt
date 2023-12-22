package com.sheinahamisi.weathersagepro.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme
import com.sheinahamisi.weathersagepro.R
import com.sheinahamisi.weathersagepro.presentation.ui.theme.LocalFonts

@Composable
fun WeatherDetail(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    value: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(39.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.primary.copy(0.10f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Text(
                text = title,
                fontFamily = LocalFonts.current.hanuman,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Black.copy(0.2f)
            )
            Text(
                text = value,
                fontFamily = LocalFonts.current.hanuman,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDetailPreview() {
    WeathersageProTheme {
        WeatherDetail(
            title = "Temperature",
            icon = R.drawable.thermometer,
            value = "16°"
        )
    }
}