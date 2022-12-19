package com.example.covidcases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

import com.example.covidcases.`interface`.CountryService
import com.example.covidcases.`object`.ServiceBuilder
import com.example.covidcases.adapter.CountriesAdapter
import com.example.covidcases.dataclass.MyCountry
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCountries()


    }

    private fun loadCountries(){

        val destinationService = ServiceBuilder.buildService(CountryService :: class.java)
        val requestCall = destinationService.getAffectedCountryList()
        requestCall.enqueue(object :retrofit2.Callback<List<MyCountry>>{
            override fun onResponse(call: Call<List<MyCountry>>,response: Response<List<MyCountry>>,
            ) {
                Log.d("Response" , "onResponse : ${response.body()}")
                if (response.isSuccessful){
                    val countryList = response.body()!!
                    Log.d("Response" , "countrylist size : ${countryList.size}")
                    covid_recycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity , 2)
                        adapter = CountriesAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@MainActivity , "Someting Went Wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<MyCountry>>, t: Throwable) {
                Toast.makeText(this@MainActivity , "Someting Went Wrong $t", Toast.LENGTH_SHORT).show()

            }

        })
    }
}