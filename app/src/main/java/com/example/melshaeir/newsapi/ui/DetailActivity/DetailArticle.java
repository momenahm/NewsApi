package com.example.melshaeir.newsapi.ui.DetailActivity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.melshaeir.newsapi.R;


public class DetailArticle extends AppCompatActivity {
 WebView webView;
 AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);
        webView = (WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        if (getIntent()!=null)
        {
            if (!getIntent().getStringExtra("webURL").isEmpty())
            {
                webView.loadUrl(getIntent().getStringExtra("webURL"));
            }
        }
    }
}
