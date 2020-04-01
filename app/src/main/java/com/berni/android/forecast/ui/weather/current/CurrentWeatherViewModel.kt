package com.berni.android.forecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.berni.android.forecast.data.repository.ForecastRepository
import internal.UnitSystem
import internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = internal.UnitSystem.METRIC// get value from settings later

    val isMetric: Boolean

        get() = unitSystem == UnitSystem.METRIC

    // fetching of the weather for the fragment. Our viewmodel needs to communicate with our repository
    // and then expose that data for our view
    // the viewmodel does not know about the view, it just gives him the data


    // lazy instantiation, it will wait for the initialization until it is used
  val weather by lazyDeferred {

        forecastRepository.getCurrentWeather(isMetric)

    }

}