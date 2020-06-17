package com.shubham.covid_19track;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class India_Govt_WebActivity extends AppCompatActivity {

    WebView webview;
    ProgressBar progressBar;
    private static final String TAG="AndroidRide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        webview = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new India_Govt_WebActivity.WebViewClient());
        webview.loadUrl("https://www.mohfw.gov.in/");

        webview.setWebViewClient(new WebViewClient());

        //from this website https://androidride.com/android-webview-example-tutorial-kotlin-java-download-source-code/#android%20webview%20download%20file%20example

        webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url, final String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
                //Checking runtime permission for devices above Marshmallow.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Log.v(TAG,"Permission is granted");
                        downloadDialog(url,userAgent,contentDisposition,mimetype);
                    } else {
                        Log.v(TAG,"Permission is revoked");
                        //requesting permissions.
                        ActivityCompat.requestPermissions(India_Govt_WebActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }
                else {
                    //Code for devices below API 23 or Marshmallow
                    Log.v(TAG,"Permission is granted");
                    downloadDialog(url,userAgent,contentDisposition,mimetype);
                }
            }
        });
    }
    public void downloadDialog(final String url,final String userAgent,String contentDisposition,String mimetype)
    {
        //getting filename from url.
        final String filename = URLUtil.guessFileName(url,contentDisposition,mimetype);
        //alertdialog
        AlertDialog.Builder builder=new AlertDialog.Builder(India_Govt_WebActivity.this);
        //title of alertdialog
        builder.setTitle("Download");
        //message of alertdialog
        builder.setMessage("Do you want to save " +filename);
        //if Yes button clicks.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //DownloadManager.Request created with url.
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                //cookie
                String cookie= CookieManager.getInstance().getCookie(url);
                //Add cookie and User-Agent to request
                request.addRequestHeader("Cookie",cookie);
                request.addRequestHeader("User-Agent",userAgent);
                //file scanned by MediaScannar
                request.allowScanningByMediaScanner();
                //Download is visible and its progress, after completion too.
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //DownloadManager created
                DownloadManager downloadManager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                //Saving files in Download folder
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                //download enqued
                downloadManager.enqueue(request);

                Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //cancel the dialog if Cancel clicks
                dialog.cancel();
            }
        });
        //alertdialog shows.
        builder.create().show();


    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack())
        {
            webview.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}
