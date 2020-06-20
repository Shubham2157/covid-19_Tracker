package com.shubham.covid_19track.world

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class DetailActivity : AppCompatActivity() {

    private var positionCountry = 0

    val pieChart: PieChart
        get() = piechart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        positionCountry = intent.getIntExtra("position", 0)
        supportActionBar!!.title = "Details of " + AffectedCountries.countryModelsList[positionCountry].country
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        tvCountr!!.text = AffectedCountries.countryModelsList[positionCountry].country
        tvCases!!.text = AffectedCountries.countryModelsList[positionCountry].cases
        tvRecovered!!.text = AffectedCountries.countryModelsList[positionCountry].recovered
        tvCritical!!.text = AffectedCountries.countryModelsList[positionCountry].critical
        tvActive!!.text = AffectedCountries.countryModelsList[positionCountry].active
        tvTodayCases!!.text = AffectedCountries.countryModelsList[positionCountry].todayCases
        tvDeath!!.text = AffectedCountries.countryModelsList[positionCountry].deaths
        tvTodayDeaths!!.text = AffectedCountries.countryModelsList[positionCountry].todayDeaths

        piaChart()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun piaChart(){

        pieChart.addPieSlice(PieModel("Cases", tvCases!!.text.toString().toInt().toFloat(), Color.parseColor("#F50005")))
        pieChart.addPieSlice(PieModel("Recoverd", tvRecovered!!.text.toString().toInt().toFloat(), Color.parseColor("#66BB6A")))
        pieChart.addPieSlice(PieModel("Deaths", tvDeath!!.text.toString().toInt().toFloat(), Color.parseColor("#EF5350")))
        pieChart.addPieSlice(PieModel("Active", tvActive!!.text.toString().toInt().toFloat(), Color.parseColor("#29B6F6")))
        pieChart.startAnimation()

    }

}
