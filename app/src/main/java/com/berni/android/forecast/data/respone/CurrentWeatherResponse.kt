package com.berni.android.forecast.data.respone

import com.berni.android.forecast.data.db.entity.CurrentWeatherEntry
import com.berni.android.forecast.data.db.entity.Location
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,  //currentweatherentry
    val location: Location
) {


}