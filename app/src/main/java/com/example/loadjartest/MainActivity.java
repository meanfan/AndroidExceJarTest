package com.example.loadjartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.loadjartest.loadjar.LoadJarMethod;
import com.example.loadjartest.remote.A;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String rstStr = LoadJarMethod.acquire(this);
        Log.d(TAG, "onCreate: "+rstStr);
        String imei = new A().getIMEI(this);
        Log.d(TAG, "onCreate: imei:"+imei);
    }
}