package com.yousufjamil.regillustrationsproject

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView: WebView = findViewById(R.id.webView)
        val webViewName: TextView = findViewById(R.id.webpageNameTxt)
        val loadProgress: ProgressBar = findViewById(R.id.loadProgress)

        loadProgress.visibility = View.INVISIBLE
        webView.loadUrl("https://science-pro.repl.co/")
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.canGoBack()
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                val site = Intent(Intent.ACTION_VIEW)
                site.data = Uri.parse("https://science-pro.repl.co/")
                startActivity(site)
            }

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
            }
        }
    }
}