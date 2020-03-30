package com.berni.android.forecast.data.db.unitlocalized

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class MetricCurrentWeatherEntry (

    @ColumnInfo(name = "feelslike")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name =" precip")
    override val precipitationVolume: Double,
    @ColumnInfo(name= "temp")
    override val temperature: Double,
    @ColumnInfo(name = "uv_index")
    val uvIndex: Double,
    @SerializedName("vis")
    override val visibilityDistance: Double,
    @ColumnInfo(name = "wind_dir")
    override val windDirection: String,
    @ColumnInfo(name = "wind_speed")
    override val windSpeed: Double


) : UnitSpecificCurrentWeatherEntry
