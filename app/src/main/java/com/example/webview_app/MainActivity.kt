package com.example.webview_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView = findViewById<WebView>(R.id.webview)
        webView.setWebViewClient(object :WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                // true -> 주도권을 가져오지 않는다
                // false -> 주도권을 가져오겠다.(우리앱)
                return false
            }
        })

        try {
            webView.loadUrl(intent.data!!.toString())
        }catch (exception : Exception){

        }

        val urlPrefix = "http://"
        var finalUrl = ""

        val address = findViewById<EditText>(R.id.address)

        // addTextChangedListener 사용법
        address.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("testt", "beforeTextChanged : " + s)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("testt", "onTextChanged : " + s)
            }

            override fun afterTextChanged(s: Editable?) {
                finalUrl = urlPrefix +s.toString()
                Log.d("testt", "afterTextChanged : " + s)
            }
        })

        // addTextChangedListener 사용법 -> 람다 형식
        address.doBeforeTextChanged { text, start, count, after ->  }
        address.doOnTextChanged { text, start, before, count ->  }
        address.doAfterTextChanged {  }

        val open = findViewById<TextView>(R.id.open)
        open.setOnClickListener {
            val url = address.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}