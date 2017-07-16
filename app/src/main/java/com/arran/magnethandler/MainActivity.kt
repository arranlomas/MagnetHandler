package com.arran.magnethandler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var isAtStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        webView.settings.domStorageEnabled = true
        webView.settings.saveFormData = true
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        webView.settings.allowFileAccessFromFileURLs = true
        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.settings.setSupportZoom(true)
        webView.setWebViewClient(WebViewClient())
        webView.isClickable = true
        webView.setWebChromeClient(WebChromeClient())

        if (intent.scheme != null && intent.scheme.equals("magnet", true)) {
            val magnet = intent.dataString
            Log.v("magnet", magnet)
            isAtStart = false
            webView.loadUrl("https://torrent.express/?uri=$magnet")
        } else {
            isAtStart = false
            webView.loadUrl("https://torrent.express/login")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(isAtStart){
            finish()
            return
        }

        if (webView.canGoBack()) {
            webView.goBack()
        }
        else {
            webView.loadUrl("https://torrent.express")
            isAtStart = true
        }
    }
}
