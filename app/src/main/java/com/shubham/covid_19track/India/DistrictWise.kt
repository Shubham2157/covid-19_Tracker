package com.shubham.covid_19track.India

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.leo.simplearcloader.SimpleArcLoader
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_district_wise.*
import org.json.JSONException

class DistrictWise : AppCompatActivity() {


    private val requestQueue  by lazy {
        Volley.newRequestQueue(this)
    }


    private var stateName: String? = null

    private val simpleArcLoader: SimpleArcLoader
        get() = progressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district_wise)



        no_cases_animation!!.visibility = View.INVISIBLE
        txt_no_cases!!.visibility = View.INVISIBLE

        districtRecycler!!.layoutManager = LinearLayoutManager(this)
        stateName = intent.getStringExtra("stateName")
        txt_state_name!!.text = stateName

        parseJson()

    }
    private fun parseJson() {

        simpleArcLoader.start()

        val url = "https://api.covid19india.org/state_district_wise.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null
            , Response.Listener { response ->
                try {
                    val state = response.getJSONObject(stateName)
                    val districtData = state.getJSONObject("districtData")
                    val keys = districtData.names()
                    val length = keys.length()
                    val district = arrayOfNulls<String>(length)
                    val cases = arrayOfNulls<String>(length)
                    for (i in 0 until length) {
                        district[i] = keys.getString(i)
                        cases[i] = districtData.getJSONObject(district[i]).getString("confirmed")
                    }

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE


                    districtRecycler!!.adapter = DistrictAdapter(district, cases)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    no_cases_animation!!.visibility = View.VISIBLE
                    txt_no_cases!!.visibility = View.VISIBLE

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE

                }
            }, Response.ErrorListener { error -> error.printStackTrace()
                simpleArcLoader.stop()
                simpleArcLoader.visibility = View.GONE
            })
        requestQueue!!.add(request)
    }
}
