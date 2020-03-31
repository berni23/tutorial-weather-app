package com.berni.android.forecast.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.berni.android.forecast.R
import com.berni.android.forecast.data.network.ApixuWeatherApiService
import com.berni.android.forecast.data.network.ConnectivityInterceptorImpl
import com.berni.android.forecast.data.network.WeatherNetworkDataSource
import com.berni.android.forecast.data.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {
    private var mViewModel: CurrentWeatherViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(
            CurrentWeatherViewModel::class.java
        )
        // TODO: Use the ViewModel
        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))

        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(this,Observer{
            textView.text = it.toString()

        })

        GlobalScope.launch(Dispatchers.Main) {

            weatherNetworkDataSource.fetchCurrentWeather("Barcelona","en")


        }
    }

    companion object {
        fun newInstance(): CurrentWeatherFragment {
            return CurrentWeatherFragment()
        }
    }
}