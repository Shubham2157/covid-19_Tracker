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

    private val txt_total: TextView? = null
    private  var txt_active:TextView? = null
    private  var txt_recovered:TextView? = null
    private  var txt_deaths:TextView? = null
    private  var txt_updated:TextView? = null
    private val card_today: CardView? = null
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


        val toast1 = Toast.makeText(this, "Not Official Government data. Visit Info.", Toast.LENGTH_LONG)
        toast1.setGravity(Gravity.CENTER, 0, 0)
        toast1.show()

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
            Toast.makeText(
                this@DashBoardActivity,
                "Coming Soon...",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    private fun parseJson() {
        val url = "https://api.covid19india.org/data.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null
            , Response.Listener { response ->
                response1 = response
                try {
                    val array = response.getJSONArray("statewise")
                    val `object` = array.getJSONObject(0)
                    Log.d("dataaa", `object`.toString())
                    txt_active!!.text = `object`.getString("active")
                    txt_total!!.text = `object`.getString("confirmed")
                    txt_recovered!!.text = `object`.getString("recovered")
                    txt_deaths!!.text = `object`.getString("deaths")
                    val lastUpdated =
                        "Last Updated: " + `object`.getString("lastupdatedtime")
                    txt_updated!!.text = lastUpdated
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue!!.add(request)
    }
}
