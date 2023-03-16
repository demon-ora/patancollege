package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Singlepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlepage);
        WebView webview = findViewById(R.id.webview);
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        webview.setWebViewClient(new WebViewClient());
webview.loadUrl("https://bimash.com.np/Projects/Patan/MobileDesc?b="+code);
    }
}