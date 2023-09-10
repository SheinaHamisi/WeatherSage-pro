package com.sheinahamisi.weathersagepro.data.mappers

import com.sheinahamisi.weathersagepro.data.dto.DailyDto
import com.sheinahamisi.weathersagepro.domain.model.Daily

fun DailyDto.toDaily(): Daily {
    return Daily(
        dewPoint = dewPoint,
        dt = dt,
        temp = tempDto.day,
        humidity = humidity,
        title = weatherDto.getOrNull(0)?.main ?: "",
        description = weatherDto.getOrNull(0)?.description ?: "",
        windSpeed = windSpeed,
        icon = "https://openweathermap.org/img/wn/${weatherDto.getOrNull(0)?.icon}@2x.png"
    )
}