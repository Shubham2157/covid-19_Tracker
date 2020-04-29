package com.shubham.covid_19track.India

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shubham.covid_19track.R

class TestsReport : AppCompatActivity() {

    private var txt_total_tests_rep: TextView? = null
    private  var txt_positive_cas:TextView? = null
    private  var txt_updated_tes:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests_report)

        txt_positive_cas = findViewById(R.id.txt_positive_cas)
        txt_total_tests_rep = findViewById(R.id.txt_total_tests_rep)
        txt_updated_tes = findViewById(R.id.txt_updated_tes)


        val positiveCases = intent.getStringExtra("totalPositive")
        val totalTests = intent.getStringExtra("totalIndividuals")
        val lastUpdated = intent.getStringExtra("lastUpdated")

        txt_positive_cas!!.text = positiveCases
        txt_total_tests_rep!!.text = totalTests
        txt_updated_tes!!.text = "Last Updated: $lastUpdated"
    }
}
