package com.shubham.covid_19track.India

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shubham.covid_19track.R
import org.json.JSONException

class DistrictWiseZone : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var requestQueue: RequestQueue? = null
    private lateinit var zoneData: Array<ZoneData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district_wise_zone)

        recyclerView = findViewById(R.id.recycler1)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        requestQueue = Volley.newRequestQueue(this)
        parseJson()
    }

    private fun parseJson() {
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
                    recyclerView?.adapter = DistrictZoneAdapter(zoneData)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { }
        )
        requestQueue?.add(request)
    }
}
