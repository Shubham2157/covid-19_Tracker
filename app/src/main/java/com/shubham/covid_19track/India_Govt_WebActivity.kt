package com.shubham.covid_19track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class India_Govt_WebActivity : AppCompatActivity() {

    private var mywebView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)
        mywebView = findViewById(R.id.webView)
        val webSettings = mywebView!!.settings
        webSettings.javaScriptEnabled = true
        mywebView!!.loadUrl("https://www.mohfw.gov.in/")
        mywebView!!.webViewClient = WebViewClient()
    }

    override fun onBackPressed() {
        if (mywebView!!.canGoBack()) {
            mywebView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
