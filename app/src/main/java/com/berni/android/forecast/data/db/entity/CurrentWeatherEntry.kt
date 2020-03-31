package com.berni.android.forecast.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



const val CURRENT_WEATHER_ID = 0


@Entity(tableName = "CurrentWeather")  // Room just works with primitive data types ( not classes), for that,  use @Embedded)
data class CurrentWeatherEntry(

    @SerializedName("feelslike")
    val feelslike: Double,
   @SerializedName("is_day")
    val isDay: String,
   // @SerializedName("observation_time")
   // val observationTime: String,
    @SerializedName("precipitation")
    val precip: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("uvIndex")
    val uvIndex: Double,
    @SerializedName("vis")
    val vis: Double,
   // @SerializedName("weather_code")
   // val weatherCode: Double,
    @SerializedName("windDir")
    val windDir: String,
    @SerializedName("windSpeed")
    val windSpeed: Double,

    @PrimaryKey(autoGenerate = false) // we do not want the room library to generate a primary key, as we
                                      //   want it constant

    var id: Int = CURRENT_WEATHER_ID
    // @SerializedName("weather_descriptions")
    //  val weatherDescriptions: List<String>,
    //  @SerializedName("weather_icons")
    //  val weatherIcons: List<String>,
    //  @SerializedName("wind_degree")
    // val windDegree: Double,
    // val cloudcover: Double,
    // val pressure: Double,
    //  val humidity: Double,
)