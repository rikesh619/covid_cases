package com.example.covidcases.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidcases.R
import com.example.covidcases.dataclass.MyCountry
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.covid_recycler.view.*

class CountriesAdapter(private val countriesList: List<MyCountry>) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.covid_recycler , parent , false )

        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       Log.d("Response" , "List Count : ${countriesList.size}")

        return holder.bind(countriesList[position])
    }

    override fun getItemCount(): Int {
       return countriesList.size
    }

    class CountryViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

        var flag = itemView.findViewById<ImageView>(R.id.flag_img)
        var cases = itemView.findViewById<TextView>(R.id.cases_covid)
        var title = itemView.findViewById<TextView>(R.id.title_covid)
        var death = itemView.findViewById<TextView>(R.id.death_covid)
        var recovered = itemView.findViewById<TextView>(R.id.recovered_covid)

        fun bind(country : MyCountry){

            val casesrecord = "Cases : ${country.cases.toString()} "
            val deathrecord = "Deaths : ${country.deaths.toString()} "
            val recoveredrecord = "Recovered : ${country.recovered.toString()} "
            title.text = country.country
            cases.text = casesrecord
            death.text = deathrecord
            recovered.text = recoveredrecord
            Picasso.get().load(country.countryInfo.flag).into(flag)
        }
    }
}