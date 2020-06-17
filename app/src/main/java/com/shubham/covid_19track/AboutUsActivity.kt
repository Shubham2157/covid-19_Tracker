package com.shubham.covid_19track

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about_us.*


class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        mail_shubham.setOnClickListener {
            composeEmail(arrayOf("kumarshubhamjha2157@gmail.com"),"Regarding Your App Covid !9 Tracker ")
        }

        whatsapp_shubham.setOnClickListener{
            composewhatsapp("+91 7609048674")
        }


        facebook_shubham.setOnClickListener {

            val facebookIntent = openFacebook(this.applicationContext, "100009608210873","subham.kishan.12")
            startActivity(facebookIntent)
            openFacebook(applicationContext , "100009608210873","subham.kishan.12")
        }

        web_shubham.setOnClickListener {
            openWebPage("https://shubhamkumarjha.herokuapp.com/")
        }

        insta_shubham.setOnClickListener {

            // from ==> https://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android

            val uri = Uri.parse("http://instagram.com/_u/kumarjhashubham0000/?hl=en")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("com.instagram.android")

            try {
                startActivity(likeIng)
                }

            catch (e: ActivityNotFoundException)
            {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/kumarjhashubham0000/?hl=en")))
            }
        }
    }

    private fun composeEmail(addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(applicationContext.packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun composewhatsapp(contact: String) {
        val url = "https://api.whatsapp.com/send?phone=$contact"
        try {
            val pm = applicationContext.packageManager
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(applicationContext, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }


    private fun openFacebook(context: Context, id: String, url: String): Intent? {
        return try {
            context.packageManager
                .getPackageInfo("com.facebook.katana", 0)
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("fb://profile/$id")
            )
        } catch (e: Exception) {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/$url")
            )
        }
    }

    fun openWebPage(url: String?) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}
