package com.berni.android.forecast.data.respone

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,  //currentweatherentry
    val location: Location
) {


}