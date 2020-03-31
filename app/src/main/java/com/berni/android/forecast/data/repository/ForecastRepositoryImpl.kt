package com.berni.android.forecast.data.repository

import androidx.lifecycle.LiveData
import com.berni.android.forecast.data.db.CurrentWeatherDao
import com.berni.android.forecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.berni.android.forecast.data.network.WeatherNetworkDataSource
import com.berni.android.forecast.data.respone.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(

    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource

): ForecastRepository {

    init {

        weatherNetworkDataSource.downloadedCurrentWeather.observeForever {  // observe forever, as  there is no lifecyce
                newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {

        return withContext(Dispatchers.IO) {

            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherMetric() // should be getWeatherImperial in case data with imperial units is avaliable
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {

        GlobalScope.launch(Dispatchers.IO) {

            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)

        }
    }  // we can use globalscope because the repository does not have a lifecycle

    private suspend fun initWeatherData() {

        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()


    }

    private suspend fun fetchCurrentWeather() {

        weatherNetworkDataSource.fetchCurrentWeather(

            "Terrassa",
            Locale.getDefault().language
        ) // get default language from device
        // function does not return anything; it fetches the data and actualizes the Live data

    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {

        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)


    }
}