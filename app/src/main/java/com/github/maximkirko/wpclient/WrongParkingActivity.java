package com.github.maximkirko.wpclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.maximkirko.wpclient.app.models.Coords;
import com.github.maximkirko.wpclient.app.models.Photo;
import com.github.maximkirko.wpclient.app.models.TestClass;
import com.github.maximkirko.wpclient.app.models.Ticket;
import com.github.maximkirko.wpclient.app.models.ViolationEnum;
import com.github.maximkirko.wpclient.utils.*;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private String res = "";

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

        ArrayAdapter<?> adapter = new ArrayAdapter<ViolationEnum>(this,
                android.R.layout.simple_spinner_item, ViolationEnum.values());
        violationsSpinner.setAdapter(adapter);
    }

    public void onAddressUpdateClick(View view) {
        Utils.setLocation(etAddress);
    }

    public void onDateUpdateClick(View view) {
        Utils.setDate(etDate);
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

            send();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CANCELED) {
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

    public void onButtonHintClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Справка")
                .setMessage(R.string.button_hint_text)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private Ticket makeTicket() {
        Ticket ticket = new Ticket();

        Set<Photo> images = new HashSet<Photo>();

        Photo photo = new Photo();
        photo.setPhoto(Utils.imageToByteArray(im));
        images.add(photo);
        photo.setPhoto(Utils.imageToByteArray(im2));
        images.add(photo);
        photo.setPhoto(Utils.imageToByteArray(im3));
        images.add(photo);

        ticket.setViolationPhotos(images);
        ticket.setViolation(ViolationEnum.values()[violationsSpinner.getSelectedItemPosition()]);
        ticket.setLicensePlate(etLicensePlate.getText().toString());
        ticket.setAddress(etAddress.getText().toString());

        Coords coords = new Coords(Geolocation.imHere.getLatitude(), Geolocation.imHere.getLongitude());

        ticket.setLocation(coords.toString());
        ticket.setDate(new Date(Date.parse(etDate.getText().toString())));
        ticket.setComment(etComment.getText().toString());

        return ticket;
    }

    private void send() {

        new Thread(new Runnable() {
            public void run() {

                try {

                    URL url = new URL("http://10.0.2.2:8080/android");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");

                    Ticket ticket = makeTicket();

                    JSONObject jObj = Utils.quickParse(ticket);

                    System.out.println(jObj.toString());

                    OutputStream os = conn.getOutputStream();
                    os.write(jObj.toString().getBytes());
                    os.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
