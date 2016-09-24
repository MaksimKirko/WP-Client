package com.github.maximkirko.wpclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //view components
    private Button btViolPhoto;
    private Button btNumPhoto;
    private ImageView violPhoto;
    private ImageView numPhoto;

    //popups
    private AlertDialog.Builder ad;

    //objects for load photos from gallery
    private static final int GALLERY_REQUEST = 1;
    private Uri cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btViolPhoto = (Button) findViewById(R.id.buttonViolPhoto);
        btNumPhoto = (Button) findViewById(R.id.buttonNumPhoto);
        violPhoto = (ImageView) findViewById(R.id.imageView);
        numPhoto = (ImageView) findViewById(R.id.imageView2);

        ad = new AlertDialog.Builder(MainActivity.this);
        ad.setTitle(R.string.dialog_photo_title);
        ad.setPositiveButton(R.string.dialog_photo_choice_1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        cache = imageReturnedIntent.getData();
    }

    public void onClickViolPhoto(View view) {
        ad.show();
        if(cache != null) {
            violPhoto.setImageURI(cache);
            cache = null;
        }
    }

    public void onClickNumPhoto(View view) {
        ad.show();
        if(cache != null) {
            numPhoto.setImageURI(cache);
            cache = null;
        }
    }
}
