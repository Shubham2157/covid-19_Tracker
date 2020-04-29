package com.shubham.covid_19track.India

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

class StateWiseReport : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var requestQueue: RequestQueue? = null
    private lateinit var stateData: Array<StateData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_wise_report)

        recyclerView = findViewById(R.id.recycler)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        requestQueue = Volley.newRequestQueue(this)
        parseJson()
    }

    private fun parseJson() {
        val url = "https://api.covid19india.org/data.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val array = response.getJSONArray("statewise")
                    val gsonBuilder = GsonBuilder()
                    val gson: Gson = gsonBuilder.create()
                    stateData = gson.fromJson(
                        array.toString(),
                        Array<StateData>::class.java
                    )
                    recyclerView?.adapter = myAdapter(stateData)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { }
        )
        requestQueue?.add(request)
    }
}
