package com.sheinahamisi.weathersagepro.data.mappers

import com.sheinahamisi.weathersagepro.data.dto.OpenWeatherResponseDto
import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse

fun OpenWeatherResponseDto.toOpenWeatherResponse(): OpenWeatherResponse {
    return OpenWeatherResponse(
        dailyWeatherList = dailyDto.map { it.toDaily() },
        timeZone = timezone,
        lat = lat,
        lon = lon,
        current = currentDto.toCurrent()
    )
}