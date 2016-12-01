package com.example.dnikolic.testformapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.lang.Object;

//import android.support.test.espresso.contrib.CountingIdlingResource;


public class IdleResourceActivity extends AppCompatActivity {

    //CountingIdlingResource idlingResource = new CountingIdlingResource("DATA_LOADER");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_resource);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //((TextView)findViewById(R.id.main_text)).setText(R.string.application_loaded);

        /* new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                ((TextView) findViewById(R.id.main_text)).setText(R.string.application_loaded);
            }
        }.execute();*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((TextView) findViewById(R.id.main_text)).setText(R.string.application_loaded);
                       //idlingResource.decrement();
                    }
                });
            }
        }).start();
    }
}
