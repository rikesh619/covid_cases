package com.example.covidcases.`interface`

import retrofit2.Call
import com.example.covidcases.dataclass.MyCountry
import retrofit2.http.GET

interface CountryService {
    @GET("countries")
    fun getAffectedCountryList(): Call<List<MyCountry>>
}