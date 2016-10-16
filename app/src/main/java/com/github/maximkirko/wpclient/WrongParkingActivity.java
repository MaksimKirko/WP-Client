package com.github.maximkirko.wpclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.maximkirko.wpclient.app.models.Coords;
import com.github.maximkirko.wpclient.app.models.Ticket;
import com.github.maximkirko.wpclient.app.models.violations.Violations;
import com.github.maximkirko.wpclient.utils.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WrongParkingActivity extends AppCompatActivity {
    //view elements
    private ImageView im;
    private ImageView im2;
    private ImageView im3;
    private EditText etLicensePlate;
    private EditText etAddress;
    private EditText etDate;
    private EditText etComment;
    private Spinner violationsSpinner;

    //objects for load photos from gallery and camera
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        im = (ImageView) findViewById(R.id.imageView);
        im2 = (ImageView) findViewById(R.id.imageView2);
        im3 = (ImageView) findViewById(R.id.imageView3);
        etLicensePlate = (EditText) findViewById(R.id.editTextLicensePlate);
        etAddress = (EditText) findViewById(R.id.editTextAddress);
        Utils.setLocation(etAddress);

        etDate = (EditText) findViewById(R.id.editTextDate);
        Utils.setDate(etDate);

        etComment = (EditText) findViewById(R.id.editTextComment);
        violationsSpinner = (Spinner) findViewById(R.id.spinnerViolType);

        ArrayAdapter<?> adapter = new ArrayAdapter<Violations>(this,
                android.R.layout.simple_spinner_item, Violations.values());
        violationsSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ticket_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_send) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            current.setImageBitmap(imageBitmap);
            current.setBackground(null);
        }
    }

    public void onImageViewClick(View view) {
        current = (ImageView) view;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private Ticket makeTicket() {
        Ticket ticket = new Ticket();

        List<byte[]> images = new ArrayList<byte[]>();
        images.add(Utils.imageToByteArray(im));
        images.add(Utils.imageToByteArray(im2));
        images.add(Utils.imageToByteArray(im3));

        ticket.setViolationPhotos(images);
        ticket.setViolation(Violations.values()[violationsSpinner.getSelectedItemPosition()]);
        ticket.setLicensePlate(etLicensePlate.getText().toString());
        ticket.setAddress(etAddress.getText().toString());
        ticket.setLocation(new Coords(Geolocation.imHere.getLatitude(), Geolocation.imHere.getLongitude()));
        ticket.setDate(new Date(Date.parse(etDate.getText().toString())));
        ticket.setComment(etComment.getText().toString());

        return ticket;
    }
}
