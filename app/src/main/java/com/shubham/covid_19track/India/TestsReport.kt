package com.shubham.covid_19track.India

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_tests_report.*

class TestsReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests_report)

        val positiveCases = intent.getStringExtra("totalPositive")
        val totalTests = intent.getStringExtra("totalIndividuals")
        val lastUpdated = intent.getStringExtra("lastUpdated")

        txt_positive_cas!!.text = positiveCases
        txt_total_tests_rep!!.text = totalTests
        txt_updated_tes!!.text = "Last Updated: $lastUpdated"
    }
}
