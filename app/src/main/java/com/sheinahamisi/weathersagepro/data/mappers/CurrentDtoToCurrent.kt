package com.sheinahamisi.weathersagepro.data.mappers

import com.sheinahamisi.weathersagepro.data.dto.CurrentDto
import com.sheinahamisi.weathersagepro.domain.model.Current

fun CurrentDto.toCurrent(): Current {
    return Current(
        dewPoint = dewPoint,
        dt = dt,
        temp = temp,
        humidity = humidity,
        title = weatherDto.getOrNull(0)?.main ?: "",
        icon = weatherDto.getOrNull(0)?.icon ?: "",
        description = weatherDto.getOrNull(0)?.description ?: "",
        windSpeed = windSpeed
    )
}