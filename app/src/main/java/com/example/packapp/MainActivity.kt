package com.example.packapp

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


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //不显示程序的标题栏
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        //不显示系统的标题栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT < 19) { // lower api
            window.decorView .systemUiVisibility = View.GONE
        } else {
            //for new api versions.
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            window.decorView .systemUiVisibility = uiOptions
        }
        setContentView(R.layout.activity_main)

        mWebView.loadUrl("https://mgame.xianquyouxi.com/yldj/index.html")
        val settings = mWebView.settings
        settings.setJavaScriptEnabled(true)
        settings.setUserAgentString("ni/1.0")
    }
}

