package com.yousufjamil.regillustrationsproject

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView: WebView = findViewById(R.id.webView)
        val webViewName: TextView = findViewById(R.id.webpageNameTxt)
        val loadProgress: ProgressBar = findViewById(R.id.loadProgress)
//        val refreshWebBtn: ImageButton = findViewById(R.id.refreshImgBtn)
        val homeWebBtn: ImageButton = findViewById(R.id.homeImgBtn)

        homeWebBtn.visibility = View.INVISIBLE
        loadProgress.visibility = View.INVISIBLE
        webView.loadUrl("https://ginastic.co/projects/reg-project/")
//        webView.loadUrl("https://replit.com/")
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.canGoBack()

//        refreshWebBtn.setOnClickListener {
//            val currentUrl = webView.url
//            if (currentUrl != null) {
//                webView.loadUrl(currentUrl)
//            }
//        }

        homeWebBtn.setOnClickListener {
            webView.loadUrl("https://ginastic.co/projects/reg-project/")
            homeWebBtn.visibility = View.INVISIBLE
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                val site = Intent(Intent.ACTION_VIEW)
                site.data = Uri.parse("https://ginastic.co/projects/reg-project/")
                startActivity(site)
            }
//            override fun onReceivedError(
//                view: WebView?,
//                request: WebResourceRequest?,
//                error: WebResourceError?
//            ) {
//                val site = Intent(Intent.ACTION_VIEW)
//                site.data = Uri.parse("https://science-pro.repl.co/")
//                startActivity(site)
//            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadProgress.visibility = View.VISIBLE
                webView.setOnTouchListener(View.OnTouchListener { v, event -> true })
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadProgress.visibility = View.INVISIBLE
                webViewName.setText(view?.title)
                webView.setOnTouchListener(View.OnTouchListener { v, event -> false })
                if (webView.url == "https://ginastic.co/projects/reg-project/") {
                    homeWebBtn.visibility = View.INVISIBLE
                } else {
                    homeWebBtn.visibility = View.VISIBLE
                }
            }
        }
    }
}