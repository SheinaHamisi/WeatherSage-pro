package com.sheinahamisi.weathersagepro.data.mappers

import com.sheinahamisi.weathersagepro.data.dto.CurrentDto
import com.sheinahamisi.weathersagepro.domain.model.Current

fun CurrentDto.toCurrent(): Current {
    return Current(
        dewPoint = dewPoint ?: 0.0,
        dt = dt ?: 0,
        temp = temp ?: 0.0,
        humidity = humidity ?: 0,
        title = weatherDto?.getOrNull(0)?.main ?: "",
        icon = "https://openweathermap.org/img/wn/${weatherDto?.getOrNull(0)?.icon}@2x.png",
        description = weatherDto?.getOrNull(0)?.description ?: "",
        windSpeed = windSpeed ?: 0.0
    )
}