package com.shubham.covid_19track.India

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.leo.simplearcloader.SimpleArcLoader
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_state_wise_report.*
import org.json.JSONException

class StateWiseReport : AppCompatActivity() {

    private val recyclerView: RecyclerView
        get() = recycler

    private val requestQueue  by lazy {
        Volley.newRequestQueue(this)
    }

    private val simpleArcLoader: SimpleArcLoader
        get() = progressBar

    private lateinit var stateData: Array<StateData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_wise_report)


        recyclerView.layoutManager = LinearLayoutManager(this)

        parseJson()
    }

    private fun parseJson() {

        simpleArcLoader.start()

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

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE

                    recyclerView.adapter = myAdapter(stateData)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE
                }
            },
            Response.ErrorListener {error ->
                simpleArcLoader.stop()
                simpleArcLoader.visibility = View.GONE

                Toast.makeText(this@StateWiseReport, error.message, Toast.LENGTH_SHORT).show()

            }
        )
        requestQueue?.add(request)
    }
}
