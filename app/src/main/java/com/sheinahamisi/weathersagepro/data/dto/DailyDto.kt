package com.sheinahamisi.weathersagepro.data.dto

import com.google.gson.annotations.SerializedName

data class DailyDto(
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    val dt: Int,
    @SerializedName("feels_like")
    val feelsLikeDto: FeelsLikeDto,
    val humidity: Int,
    @SerializedName("moon_phase")
    val moonPhase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val summary: String,
    val sunrise: Int,
    val sunset: Int,
    @SerializedName("temp")
    val tempDto: TempDto,
    val uvi: Double,
    @SerializedName("weather")
    val weatherDto: List<WeatherDto>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)