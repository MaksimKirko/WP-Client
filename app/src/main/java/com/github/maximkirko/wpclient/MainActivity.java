package com.github.maximkirko.wpclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //view components
    private Button btViolPhoto;
    private Button btLPPhoto;
    private ImageView violPhoto;
    private ImageView lpPhoto;

    //popups
    private AlertDialog.Builder ad;

    //objects for load photos from gallery
    private final int GALLERY_REQUEST = 1;
    private Uri cache;

    //objects for make photo with camera
    private final int CAMERA_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btViolPhoto = (Button) findViewById(R.id.buttonViolPhoto);
        btLPPhoto = (Button) findViewById(R.id.buttonLPPhoto);
        violPhoto = (ImageView) findViewById(R.id.imageView);
        lpPhoto = (ImageView) findViewById(R.id.imageView2);

        ad = new AlertDialog.Builder(MainActivity.this);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_RESULT) {
            cache = data.getData();
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
            cache = data.getData();
        }
    }

    public void onClickViolPhoto(View view) {
        ad.show();
        if(cache != null) {
            violPhoto.setImageURI(cache);
            cache = null;
        }
    }

    public void onClickLPPhoto(View view) {
        ad.show();
        if(cache != null) {
            lpPhoto.setImageURI(cache);
            cache = null;
        }
    }
}
