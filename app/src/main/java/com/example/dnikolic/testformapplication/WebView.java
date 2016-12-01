package com.example.dnikolic.testformapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewClient;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class WebView extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);


        android.webkit.WebView htmlWebView = (android.webkit.WebView) findViewById(R.id.html_view);
        htmlWebView.getSettings().setJavaScriptEnabled(true);


        @VisibleForTesting
        String customHtml = "<html><body><h3>WebView simple form</h3>" +
                "<form>" +
                "User name:<br>" +
                "<input type='text' name='name' value='Laslo Vader'><br>" +
                "User password:<br>" +
                "<input type='text' name='password' value='exlibrius'><br>" +
                "<input type='radio' name='gender' value='male' checked>Male<br>" +
                "<input type='radio' name='gender' value='female'>Female<br><br>" +
                "<input type='submit' name='submit' value='Submit'>" +
                "<input type='reset' name='reset'>" +
                "</form>" +
                "</body></html>";

        htmlWebView.loadData(customHtml, "text/html", "UTF-16");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}









