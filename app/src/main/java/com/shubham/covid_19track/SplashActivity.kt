package com.shubham.covid_19track

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private var txt_error: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        txt_error = findViewById(R.id.txt_error)
        txt_error!!.setVisibility(View.INVISIBLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            confirmInternetConnection(4150)
        } else {
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                finish()
                startActivity(intent)
            }, 4150)
        }

        txt_error!!.setOnClickListener(View.OnClickListener {
            txt_error!!.setVisibility(View.INVISIBLE)
            confirmInternetConnection(500)
        })
    }
    private fun confirmInternetConnection(delay: Int) {
        val isConnected = checkForInternetConnection()
        if (isConnected) {
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this@SplashActivity , OnBoardingActivity::class.java)
                finish()
                startActivity(intent)
            }, delay.toLong())
        } else {
            txt_error!!.visibility = View.VISIBLE
        }
    }


    private fun checkForInternetConnection(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
