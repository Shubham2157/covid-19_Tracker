package com.shubham.covid_19track.India

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.shubham.covid_19track.R
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.pia_chart_sheet.view.*
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import org.json.JSONException
import org.json.JSONObject

class DashBoardActivity : AppCompatActivity() {

    private val requestQueue  by lazy {
        Volley.newRequestQueue(this)
    }

    private var response1: JSONObject? = null

    val pieChart: PieChart
        get() = piechart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

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
                intent.putExtra("totalIndividuals",`object`.getString("totalsamplestested"))
                intent.putExtra("totalPositive", `object`.getString("totalpositivecases"))
               intent.putExtra("lastUpdated", `object`.getString("updatetimestamp"))
                startActivity(intent)
            } catch (e: JSONException) {
               e.printStackTrace()
           }

       }

       card_state!!.setOnClickListener {
            val intent = Intent(this@DashBoardActivity, StateWiseReport::class.java)
           startActivity(intent)
        }

       card_district!!.setOnClickListener {
           val intent = Intent(this@DashBoardActivity, SelectState::class.java)
            startActivity(intent)
       }

        card_pia!!.setOnClickListener {

            val mBottomSheetDialog = RoundedBottomSheetDialog(context = this)
            val sheetView = layoutInflater.inflate(R.layout.pia_chart_sheet, null)
            mBottomSheetDialog.setContentView(sheetView)

            sheetView.piechart.addPieSlice(PieModel("Cases", txt_total!!.text.toString().toInt().toFloat(), Color.parseColor("#F50005")))
            sheetView.piechart.addPieSlice(PieModel("Recoverd", txt_recovered!!.text.toString().toInt().toFloat(), Color.parseColor("#66BB6A")))
            sheetView.piechart.addPieSlice(PieModel("Deaths", txt_deaths!!.text.toString().toInt().toFloat(), Color.parseColor("#EF5350")))
            sheetView.piechart.addPieSlice(PieModel("Cases", txt_active!!.text.toString().toInt().toFloat(), Color.parseColor("#29B6F6")))
            pieChart.startAnimation()
            mBottomSheetDialog.show()



        }

        card_zone!!.setOnClickListener {
            val intent = Intent(this@DashBoardActivity, DistrictWiseZone::class.java)
            startActivity(intent)
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
