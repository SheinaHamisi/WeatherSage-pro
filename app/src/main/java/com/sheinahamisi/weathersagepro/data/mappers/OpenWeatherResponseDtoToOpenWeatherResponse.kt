package com.sheinahamisi.weathersagepro.data.mappers

import com.sheinahamisi.weathersagepro.data.dto.OpenWeatherResponseDto
import com.sheinahamisi.weathersagepro.domain.model.Current
import com.sheinahamisi.weathersagepro.domain.model.Daily
import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse

fun OpenWeatherResponseDto.toOpenWeatherResponse(): OpenWeatherResponse {
    return OpenWeatherResponse(
        dailyWeatherList = dailyDto?.map { it?.toDaily() ?: Daily(
            0.0,
            0,
            0.0,
            0,
            "",
            "",
            "",
            0.0
        ) } ?: listOf(),
        timeZone = timezone,
        lat = lat,
        lon = lon,
        current = currentDto?.toCurrent() ?: Current(0.0, 0, 0.0, 0, "", "", "", 0.0)
    )
}