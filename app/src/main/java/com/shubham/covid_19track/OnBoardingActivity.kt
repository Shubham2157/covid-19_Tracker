package com.shubham.covid_19track

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.covid_19track.India.DashBoardActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        world_layout.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))

        }

        india_layout.setOnClickListener {
            startActivity(Intent(applicationContext, DashBoardActivity::class.java))
        }
    }
}