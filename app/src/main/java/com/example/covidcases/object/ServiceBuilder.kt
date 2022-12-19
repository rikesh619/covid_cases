package com.example.covidcases.`object`

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "https://disease.sh/v2/"

    private val okHTTP = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        okHTTP.build())

    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class <T>) : T {

        return retrofit.create(serviceType)
    }
}