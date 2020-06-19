package com.shubham.covid_19track.world

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.leo.simplearcloader.SimpleArcLoader
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_affected_countries.*
import kotlinx.android.synthetic.main.activity_help_line_no.*
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList

class AffectedCountries : AppCompatActivity() {

    var edtSearch: EditText? = null
    var listView: ListView? = null
    private val simpleArcLoader: SimpleArcLoader
        get() = loader
    var countryModel: CountryModel? = null
    var myCustomAdapter: MyCustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affected_countries)


        edtSearch = findViewById(R.id.edtSearch)
        listView = findViewById(R.id.listView)

        supportActionBar!!.title = "Affected Countries"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        fetchData()

        listView!!.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                startActivity(Intent(applicationContext, DetailActivity::class.java)
                    .putExtra("position", position))
            }

        edtSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                myCustomAdapter!!.filter.filter(s)
                myCustomAdapter!!.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun fetchData() {

        simpleArcLoader.start()

        val url = "https://corona.lmao.ninja/v2/countries/"
        val request = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                try {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val countryName = jsonObject.getString("country")
                        val cases = jsonObject.getString("cases")
                        val todayCases = jsonObject.getString("todayCases")
                        val deaths = jsonObject.getString("deaths")
                        val todayDeaths = jsonObject.getString("todayDeaths")
                        val recovered = jsonObject.getString("recovered")
                        val active = jsonObject.getString("active")
                        val critical = jsonObject.getString("critical")
                        val `object` = jsonObject.getJSONObject("countryInfo")
                        val flagUrl = `object`.getString("flag")
                        countryModel = CountryModel(flagUrl, countryName, cases, todayCases, deaths, todayDeaths, recovered, active, critical)
                        countryModelsList.add(countryModel!!)
                    }
                    myCustomAdapter = MyCustomAdapter(this@AffectedCountries, countryModelsList)
                    listView!!.adapter = myCustomAdapter

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE

                } catch (e: JSONException) {
                    e.printStackTrace()
                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE
                }
            }, Response.ErrorListener { error ->
                simpleArcLoader.stop()
                simpleArcLoader.visibility = View.GONE
                Toast.makeText(this@AffectedCountries, error.message, Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }

    companion object {
        var countryModelsList: MutableList<CountryModel> = ArrayList()
    }

}