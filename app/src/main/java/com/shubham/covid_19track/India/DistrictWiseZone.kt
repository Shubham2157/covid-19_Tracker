package com.shubham.covid_19track.India

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leo.simplearcloader.SimpleArcLoader
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_district_wise_zone.*
import kotlinx.android.synthetic.main.activity_help_line_no.*
import kotlinx.android.synthetic.main.activity_help_line_no.progressBar
import org.json.JSONException

class DistrictWiseZone : AppCompatActivity() {

    private val recyclerView: RecyclerView
        get() = recycler1

    private val requestQueue  by lazy {
        Volley.newRequestQueue(this)
    }

    private val simpleArcLoader: SimpleArcLoader
        get() = progressBar

    private lateinit var zoneData: Array<ZoneData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district_wise_zone)


        recyclerView.layoutManager = LinearLayoutManager(this)
        parseJson()
    }

    private fun parseJson() {

        simpleArcLoader.start()

        val url = "https://api.covid19india.org/zones.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val array = response.getJSONArray("zones")
                    val gsonBuilder = GsonBuilder()
                    val gson: Gson = gsonBuilder.create()
                    zoneData = gson.fromJson(
                        array.toString(),
                        Array<ZoneData>::class.java
                    )
                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE

                    recyclerView.adapter = DistrictZoneAdapter(zoneData)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE

                }
            },
            Response.ErrorListener {error ->
                simpleArcLoader.stop()
                simpleArcLoader.visibility = View.GONE
                Toast.makeText(this@DistrictWiseZone, error.message, Toast.LENGTH_SHORT).show()

            }
        )
        requestQueue?.add(request)
    }
}
