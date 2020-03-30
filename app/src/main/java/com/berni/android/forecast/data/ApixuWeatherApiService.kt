package com.berni.android.forecast.data

import com.berni.android.forecast.data.respone.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "829e6041af588bf077f2601786677121"  // const val

//http://api.weatherstack.com/current?access_key=829e6041af588bf077f2601786677121

interface ApixuWeatherApiService {

    @GET("current")
    fun getCurrentWeather(

        @Query("query") location: String,
        @Query("lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

   // http://api.weatherstack.com/current?access_key=829e6041af588bf077f2601786677121&query=New%20York

   // http://api.weatherstack.com/current?access_key=829e6041af588bf077f2601786677121&query=Terrassa

    companion object {

        operator fun invoke(): ApixuWeatherApiService {   // does not necessarily need to be invoke, but it makes it easier to use

            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)

        }


    }
}
