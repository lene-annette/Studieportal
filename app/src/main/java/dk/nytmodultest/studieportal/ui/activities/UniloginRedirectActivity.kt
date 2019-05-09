package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import dk.nytmodultest.studieportal.R

class UniloginRedirectActivity : AppCompatActivity() {

    private var url = MainActivity.UniUrl



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContentView(R.layout.activity_unilogin_redirect)
        val webview: WebView = findViewById(R.id.webview)
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object: WebViewClient(){
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean{
                    if(url.startsWith("http://10.0.2.2:3000/app-unilogin")){
                        var i = Intent(context, UniloginActivity::class.java)
                        i.putExtra("url", url)
                        finish()
                        startActivity(i)
                    }
                    view.loadUrl(url)
                    return true
                }
        }
        webview.loadUrl(url)
    }
}
