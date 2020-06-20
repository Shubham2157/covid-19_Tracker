package com.shubham.covid_19track.India

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_today_cases.*

class TodayCases : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_cases)

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
