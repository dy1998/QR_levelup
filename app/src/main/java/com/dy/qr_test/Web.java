package com.dy.qr_test;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

public class Web extends BaseActivity {
    WebView webViews;
    WebSettings WebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webViews = (WebView) findViewById(R.id.Webview);
        webViews.getSettings().setJavaScriptEnabled(true);
        WebSettings = webViews.getSettings();
        Intent intent = getIntent();
        String data = intent.getStringExtra("URL");
        webViews.loadUrl(data);
        webViews.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {
                view.loadUrl("file:///android_asset/sorry.html");
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onDestroy() {
        if (webViews != null) {
            webViews.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webViews.clearHistory();

            ((ViewGroup) webViews.getParent()).removeView(webViews);
            webViews.destroy();
            webViews = null;
        }
        super.onDestroy();
    }
}
