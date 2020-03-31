package com.berni.android.forecast.data.repository

import androidx.lifecycle.LiveData
import com.berni.android.forecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(metric:Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}