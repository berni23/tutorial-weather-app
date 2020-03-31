package com.berni.android.forecast.data.network

import androidx.lifecycle.LiveData
import com.berni.android.forecast.data.respone.CurrentWeatherResponse

interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather (

        location: String,
        languageCode: String
    )

}

