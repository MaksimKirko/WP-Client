package com.github.maximkirko.wpclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WrongParkingActivity extends AppCompatActivity {

    //view components
    private Button btViolPhoto;
    private Button btLPPhoto;
    private ImageView violPhoto;
    private ImageView lpPhoto;

    //popups
    private AlertDialog.Builder ad;

    //objects for load photos from gallery and camera
    private final int GALLERY_REQUEST = 1;
    private final int CAMERA_RESULT = 0;
    private ImageView cache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //        btViolPhoto = (Button) findViewById(R.id.buttonViolPhoto);
        //        btLPPhoto = (Button) findViewById(R.id.buttonLPPhoto);
        //        violPhoto = (ImageView) findViewById(R.id.imageView);
        //        lpPhoto = (ImageView) findViewById(R.id.imageView2);
    }

    public void buildAlertDialog() {
        cache = new ImageView(WrongParkingActivity.this);

        ad = new AlertDialog.Builder(WrongParkingActivity.this);
        ad.setTitle(R.string.dialog_photo_title);
        ad.setPositiveButton(R.string.dialog_photo_choice_1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_RESULT);
            }
        });
        ad.setNegativeButton(R.string.dialog_photo_choice_2, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        ad.setCancelable(true);

        //!!! back button pressed
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_RESULT) {
            cache.setImageURI(data.getData());
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
            cache.setImageURI(data.getData());
        }
    }

    public void onClickViolPhoto(View view) {
        violPhoto = cache;
        buildAlertDialog();
    }

    public void onClickLPPhoto(View view) {
        lpPhoto = cache;
        buildAlertDialog();
    }
}
