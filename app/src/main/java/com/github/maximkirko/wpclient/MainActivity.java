package com.github.maximkirko.wpclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.maximkirko.wpclient.utils.Geolocation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Geolocation.SetUpLocationListener(this);
    }

    public void onWrongParkingClick(View view) {
        Intent intent = new Intent(MainActivity.this, WrongParkingActivity.class);
        startActivity(intent);
    }
}
