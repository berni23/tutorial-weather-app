package com.berni.android.forecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berni.android.forecast.data.respone.CurrentWeatherResponse
import internal.NoConnectivityException

class WeatherNetworkDataSourceImpl( private val apixuWeaherApiService: ApixuWeatherApiService) : WeatherNetworkDataSource {


    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() =_downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {

        try {
            val fetchedCurrentWeather = apixuWeaherApiService
                .getCurrentWeather(location, languageCode)
                .await()

            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)

        }
        catch(e: NoConnectivityException){

            Log.e("Connectivity","no internet connection",e)
        }
    }
}