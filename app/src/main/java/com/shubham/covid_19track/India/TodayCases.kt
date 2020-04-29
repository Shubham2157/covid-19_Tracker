package com.shubham.covid_19track.India

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shubham.covid_19track.R

class TodayCases : AppCompatActivity() {

    private var txt_confirmed_today: TextView? = null
    private  var txt_deaths_tod:TextView? = null
    private  var txt_recovered_tod:TextView? = null
    private  var txt_updated_today:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_cases)

        txt_confirmed_today = findViewById(R.id.txt_confirmed_today)
        txt_deaths_tod = findViewById(R.id.txt_deaths_tod)
        txt_recovered_tod = findViewById(R.id.txt_recovered_tod)
        txt_updated_today = findViewById(R.id.txt_updated_today)


        val confirmed = intent.getStringExtra("confirmed")
        val deaths = intent.getStringExtra("deaths")
        val recovered = intent.getStringExtra("recovered")
        val lastUpdated = intent.getStringExtra("lastUpdated")

        txt_confirmed_today!!.text = confirmed
        txt_deaths_tod!!.text = deaths
        txt_recovered_tod!!.text = recovered
        txt_updated_today!!.text = "Last Updated: $lastUpdated"

    }
}
