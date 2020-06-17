package com.shubham.covid_19track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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
import com.shubham.covid_19track.India.DistrictZoneAdapter
import com.shubham.covid_19track.India.ZoneData
import kotlinx.android.synthetic.main.activity_help_line_no.*
import org.json.JSONException

class HelpLineNoActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var requestQueue: RequestQueue? = null
    var simpleArcLoader: SimpleArcLoader? = null
    private lateinit var helpData: Array<HelpData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_line_no)

        recyclerView = findViewById(R.id.recycler_help_no)
        simpleArcLoader = findViewById(R.id.progressBar)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        requestQueue = Volley.newRequestQueue(this)

        parseJson()

    }

    private fun parseJson() {

        simpleArcLoader!!.start()

        val url = "https://api.rootnet.in/covid19-in/contacts"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val array = response.getJSONObject("data").getJSONObject("contacts").getJSONArray("regional")
                    val gsonBuilder = GsonBuilder()
                    val gson: Gson = gsonBuilder.create()
                    helpData = gson.fromJson(
                        array.toString(),
                        Array<HelpData>::class.java
                    )

                    simpleArcLoader!!.stop()
                    simpleArcLoader!!.visibility = View.GONE

                    recyclerView?.adapter = HelpAdaptor(helpData)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    simpleArcLoader!!.stop()
                    simpleArcLoader!!.visibility = View.GONE
                }
            },
            Response.ErrorListener {error ->
                simpleArcLoader!!.stop()
                simpleArcLoader!!.visibility = View.GONE

                Toast.makeText(this@HelpLineNoActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue?.add(request)
    }

}

