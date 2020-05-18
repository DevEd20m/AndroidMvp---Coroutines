package com.deved.coroutinesmvp.rest

import com.deved.coroutinesmvp.BuildConfig
import com.deved.coroutinesmvp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private fun addInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val httpClient = OkHttpClient.Builder().addInterceptor(addInterceptor())
        .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
        .readTimeout(Constants.readTimeout, TimeUnit.SECONDS)
        .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
        .build()

    val endPoints: EndPoints = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().run { create<EndPoints>(EndPoints::class.java) }
}