package com.sheinahamisi.weathersagepro.data.dto

import com.google.gson.annotations.SerializedName

data class OpenWeatherResponseDto(
    val currentDto: CurrentDto?,
    val dailyDto: List<DailyDto?>?,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
)