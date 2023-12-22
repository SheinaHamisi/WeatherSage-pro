package com.sheinahamisi.weathersagepro.data.dto

import com.google.gson.annotations.SerializedName

data class OpenWeatherResponseDto(
    @SerializedName("current")
    val currentDto: CurrentDto?,
    @SerializedName("daily")
    val dailyDto: List<DailyDto?>?,
    val lat: Double?,
    val lon: Double?,
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?
)