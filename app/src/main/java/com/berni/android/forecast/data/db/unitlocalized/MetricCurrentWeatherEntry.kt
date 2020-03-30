package com.berni.android.forecast.data.db.unitlocalized

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class MetricCurrentWeatherEntry (
  //  isDay, observationTime, precip, temperature, uvIndex, visibility, weatherCode, windDir, windSpeed, id

    @ColumnInfo(name = "feelslike")
    override val feelsLike: Double,
    @ColumnInfo(name ="precip")
    override val precip: Double,
    @ColumnInfo(name= "temp")
    override val temp: Double,
    @SerializedName("vis")
    override val vis: Double,
    @ColumnInfo(name = "windDir")
    override val windDir: String,
    @ColumnInfo(name = "windSpeed")
    override val windSpeed: Double,
    @ColumnInfo(name ="uvIndex")
    override val uvIndex: Double,
    @ColumnInfo(name = "id")
    override val id: Int


) : UnitSpecificCurrentWeatherEntry
