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
import com.shubham.covid_19track.R
import org.json.JSONException

class DistrictWise : AppCompatActivity() {

    private var districtRecycler: RecyclerView? = null
    private var requestQueue: RequestQueue? = null
    private var stateName: String? = null
    private var txt_state_name: TextView? = null
    private  var txt_no_cases:TextView? = null
    private var no_cases_animation: LottieAnimationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district_wise)


        districtRecycler = findViewById(R.id.districtRecycler)
        txt_state_name = findViewById(R.id.txt_state_name)
        no_cases_animation = findViewById(R.id.no_cases_animation)
        txt_no_cases = findViewById(R.id.txt_no_cases)

        no_cases_animation!!.setVisibility(View.INVISIBLE)
        txt_no_cases!!.setVisibility(View.INVISIBLE)

        districtRecycler!!.setLayoutManager(LinearLayoutManager(this))
        stateName = intent.getStringExtra("stateName")
        txt_state_name!!.setText(stateName)
        requestQueue = Volley.newRequestQueue(this)
        parseJson()

    }
    private fun parseJson() {
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
                        cases[i] =
                            districtData.getJSONObject(district[i]).getString("confirmed")
                    }
                    districtRecycler!!.adapter = DistrictAdapter(district, cases)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    no_cases_animation!!.visibility = View.VISIBLE
                    txt_no_cases!!.visibility = View.VISIBLE
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue!!.add(request)
    }
}
