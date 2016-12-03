package com.github.maximkirko.wpclient;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.github.maximkirko.wpclient.utils.Geolocation;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Intent intent;
    Class ticketActivity;
    Button buttonGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Geolocation.SetUpLocationListener(this);


        buttonGo = (Button) findViewById(R.id.buttonGo);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        ticketActivity = WrongParkingActivity.class;
                        buttonGo.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButton2:
                        ticketActivity = WrongParkingActivity.class;
                        buttonGo.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButton3:
                        ticketActivity = WrongParkingActivity.class;
                        buttonGo.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    public void onButtonGoClick(View view) {
        intent = new Intent(MainActivity.this, ticketActivity);
        startActivity(intent);
    }
}
