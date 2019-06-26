package com.ruzhan.movie.video

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.ruzhan.movie.R
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_web_video.*

class WebVideoActivity : AppCompatActivity() {

    companion object {

        private const val VIDEO_URL: String = "URL"
        private const val MAX_PROGRESS = 80

        @JvmStatic
        fun launch(activity: Activity, url: String) {
            val intent = Intent(activity, WebVideoActivity::class.java)
            intent.putExtra(VIDEO_URL, url)
            activity.startActivity(intent)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.setFormat(PixelFormat.TRANSLUCENT)

        setContentView(R.layout.activity_web_video)

        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        web_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (load_progress.visibility != View.VISIBLE && newProgress < MAX_PROGRESS) {
                    load_progress.visibility = View.VISIBLE

                } else if (newProgress > MAX_PROGRESS) {
                    load_progress.visibility = View.INVISIBLE
                }
            }
        }
        web_view.webViewClient = WebViewClient()

        val url = intent.getStringExtra(VIDEO_URL)
        web_view.loadUrl(url)
    }

    override fun onResume() {
        web_view.onResume()
        super.onResume()
    }

    override fun onPause() {
        web_view.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        web_view.destroy()
        super.onDestroy()
    }
}