package com.sheinahamisi.weathersagepro.data.dto

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    val clouds: Int?,
    @SerializedName("dew_point")
    val dewPoint: Double?,
    val dt: Int?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    val humidity: Int?,
    val pressure: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val temp: Double?,
    val uvi: Double?,
    val visibility: Int?,
    @SerializedName("weather")
    val weatherDto: List<WeatherDto>?,
    @SerializedName("wind_deg")
    val windDeg: Int?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)