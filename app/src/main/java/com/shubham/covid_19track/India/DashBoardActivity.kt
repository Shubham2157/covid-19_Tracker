package com.shubham.covid_19track.India

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shubham.covid_19track.R
import org.json.JSONException
import org.json.JSONObject

class DashBoardActivity : AppCompatActivity() {

    private var txt_total: TextView? = null
    private  var txt_active:TextView? = null
    private  var txt_recovered:TextView? = null
    private  var txt_deaths:TextView? = null
    private  var txt_updated:TextView? = null
    private var card_today: CardView? = null
    private  var card_tests:CardView? = null
    private  var card_about:CardView? = null
    private  var card_state:CardView? = null
    private  var card_district:CardView? = null
    private  var card_myths:CardView? = null
    private var requestQueue: RequestQueue? = null
    private var response1: JSONObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)


        txt_total = findViewById(R.id.txt_total)
        txt_active = findViewById(R.id.txt_active)
        txt_recovered = findViewById(R.id.txt_recovered)
        txt_deaths = findViewById(R.id.txt_deaths)
        txt_updated = findViewById(R.id.txt_updated)
        card_today = findViewById(R.id.card_today)
        card_tests = findViewById(R.id.card_tests)
        card_about = findViewById(R.id.card_about)
        card_state = findViewById(R.id.card_state)
        card_district = findViewById(R.id.card_district)
        card_myths = findViewById(R.id.card_myths)


        requestQueue = Volley.newRequestQueue(this)
        parseJson()

        card_today!!.setOnClickListener {
            val intent = Intent(this@DashBoardActivity, TodayCases::class.java)
            try {
                val array = response1!!.getJSONArray("statewise")
               val `object` = array.getJSONObject(0)
            intent.putExtra("confirmed", `object`.getString("deltaconfirmed"))
               intent.putExtra("deaths", `object`.getString("deltadeaths"))
               intent.putExtra("lastUpdated", `object`.getString("lastupdatedtime"))
                intent.putExtra("recovered", `object`.getString("deltarecovered"))
                startActivity(intent)
           } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        card_tests!!.setOnClickListener {
            val intent = Intent(this@DashBoardActivity, TestsReport::class.java)
            try {
                val array = response1!!.getJSONArray("tested")
                val `object` = array.getJSONObject(array.length() - 1)
                intent.putExtra(
                    "totalIndividuals",
                   `object`.getString("totalindividualstested")
                )
                intent.putExtra("totalPositive", `object`.getString("totalpositivecases"))
               intent.putExtra("lastUpdated", `object`.getString("updatetimestamp"))
                startActivity(intent)
            } catch (e: JSONException) {
               e.printStackTrace()
           }
       }

        //card_about!!.setOnClickListener {
          //  val intent = Intent(this@DashBoardActivity, AboutUs::class.java)
            //startActivity(intent)
  //      }
//
       card_state!!.setOnClickListener {
            val intent = Intent(this@DashBoardActivity, StateWiseReport::class.java)
           startActivity(intent)
        }

       card_district!!.setOnClickListener {
           val intent = Intent(this@DashBoardActivity, SelectState::class.java)
            startActivity(intent)
       }

        card_myths!!.setOnClickListener {
            Toast.makeText(this@DashBoardActivity, "Coming Soon...", Toast.LENGTH_LONG).show()
        }
    }
    private fun parseJson() {
        val url = "https://api.covid19india.org/data.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                response1 = response
                try {
                    val array = response.getJSONArray("statewise")
                    val `object` = array.getJSONObject(0)
                    Log.d("dataaa", `object`.toString())
                    txt_active!!.text = `object`.getString("active")
                    txt_total!!.text = `object`.getString("confirmed")
                    txt_recovered!!.text = `object`.getString("recovered")
                    txt_deaths!!.text = `object`.getString("deaths")
                    val lastUpdated = "Last Updated: " + `object`.getString("lastupdatedtime")
                    txt_updated!!.text = lastUpdated
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue!!.add(request)
    }
}
