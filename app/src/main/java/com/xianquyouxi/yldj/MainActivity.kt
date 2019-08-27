package com.xianquyouxi.yldj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Window;
import android.view.WindowManager;
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.os.Build
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.MotionEvent




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //不显示程序的标题栏
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        //不显示系统的标题栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //隐藏虚拟按键，并且全屏
        window.decorView .systemUiVisibility = caclUiOptions()
        window.decorView.setOnSystemUiVisibilityChangeListener {
            window.decorView .systemUiVisibility = caclUiOptions()
        }
        setContentView(R.layout.activity_main)

        val settings = mWebView.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        if (Build.VERSION.SDK_INT > 16) {
            settings.allowUniversalAccessFromFileURLs  = true
        }
        settings.domStorageEnabled = true    // 默认值 false
        settings.databaseEnabled = true      // 默认值 false

        mWebView.webChromeClient = WebChromeClient()
        mWebView.webViewClient = WebViewClient()
        mWebView.setOnTouchListener { v, event -> false }
        mWebView.loadUrl("file:///android_asset/app.html")
    }
    /**
     * 重写返回回调监听
     */
    override fun onBackPressed() {
        mWebView.destroy()
        super.onBackPressed()
    }
}

fun caclUiOptions():Int{
    var uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            //布局位于状态栏下方
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            //全屏
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            //隐藏导航栏
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    if (Build.VERSION.SDK_INT >= 19) {
        uiOptions = uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    } else {
        uiOptions = uiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
    }
    return uiOptions
}
