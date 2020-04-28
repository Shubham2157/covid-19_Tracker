package com.shubham.covid_19track

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class DetailActivity : AppCompatActivity() {

    private var positionCountry = 0
    var tvCountry: TextView? = null
    var tvCases: TextView? = null
    var tvRecovered: TextView? = null
    var tvCritical: TextView? = null
    var tvActive: TextView? = null
    var tvTodayCases: TextView? = null
    var tvTotalDeaths: TextView? = null
    var tvTodayDeaths: TextView? = null
    var pieChart: PieChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        positionCountry = intent.getIntExtra("position", 0)
        supportActionBar!!.setTitle("Details of " + AffectedCountries.countryModelsList[positionCountry].country)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        tvCountry = findViewById(R.id.tvCountr)
        tvCases = findViewById(R.id.tvCases)
        tvRecovered = findViewById(R.id.tvRecovered)
        tvCritical = findViewById(R.id.tvCritical)
        tvActive = findViewById(R.id.tvActive)
        tvTodayCases = findViewById(R.id.tvTodayCases)
        tvTotalDeaths = findViewById(R.id.tvDeath)
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths)
        pieChart = findViewById(R.id.piechart)

        tvCountry!!.setText(AffectedCountries.countryModelsList[positionCountry].country)
        tvCases!!.setText(AffectedCountries.countryModelsList[positionCountry].cases)
        tvRecovered!!.setText(AffectedCountries.countryModelsList[positionCountry].recovered)
        tvCritical!!.setText(AffectedCountries.countryModelsList[positionCountry].critical)
        tvActive!!.setText(AffectedCountries.countryModelsList[positionCountry].active)
        tvTodayCases!!.setText(AffectedCountries.countryModelsList[positionCountry].todayCases)
        tvTotalDeaths!!.setText(AffectedCountries.countryModelsList[positionCountry].deaths)
        tvTodayDeaths!!.setText(AffectedCountries.countryModelsList[positionCountry].todayDeaths)

        pieChart!!.addPieSlice(PieModel("Cases", tvCases!!.text.toString().toInt().toFloat(), Color.parseColor("#FFA726")))
        pieChart!!.addPieSlice(PieModel("Recoverd", tvRecovered!!.text.toString().toInt().toFloat(), Color.parseColor("#66BB6A")))
        pieChart!!.addPieSlice(PieModel("Deaths", tvTotalDeaths!!.text.toString().toInt().toFloat(), Color.parseColor("#EF5350")))
        pieChart!!.addPieSlice(PieModel("Active", tvActive!!.text.toString().toInt().toFloat(), Color.parseColor("#29B6F6")))
        pieChart!!.startAnimation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
