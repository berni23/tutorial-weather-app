package com.berni.android.forecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.berni.android.forecast.data.db.entity.CURRENT_WEATHER_ID
import com.berni.android.forecast.data.db.entity.CurrentWeatherEntry
import com.berni.android.forecast.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun upsert (weatherEntry: CurrentWeatherEntry)   // update or insert, depending on the case

        @Query("select* from currentWeather where id = $CURRENT_WEATHER_ID")

        fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>  // theoretically, we have metric / imperial units

}