package com.shubham.covid_19track

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WhoActivity : AppCompatActivity() {
    private var mywebView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)
        mywebView = findViewById(R.id.webView)
        val webSettings = mywebView!!.settings
        webSettings.javaScriptEnabled = true
        mywebView!!.loadUrl("https://www.who.int/")
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