package com.github.maximkirko.wpclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.maximkirko.wpclient.app.models.violations.Violations;

public class WrongParkingActivity extends AppCompatActivity {
    //app structure objects
    private Violations violation;

    //view elements
    private ImageView im;
    private ImageView im2;
    private ImageView im3;

    private Spinner violationsSpinner;

    //popups
    private AlertDialog.Builder ad;

    //objects for load photos from gallery and camera
    private final int GALLERY_REQUEST = 1;
    private final int CAMERA_RESULT = 0;
    private ImageView current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        violationsSpinner = (Spinner) findViewById(R.id.spinnerViolType);

        ArrayAdapter<?> adapter = new ArrayAdapter<Violations>(this,
                android.R.layout.simple_spinner_item, Violations.values());
        violationsSpinner.setAdapter(adapter);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void buildAlertDialog() {
        ad = new AlertDialog.Builder(WrongParkingActivity.this);
        ad.setTitle(R.string.add_photo);
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
        if(requestCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == CAMERA_RESULT) {
            current.setImageURI(data.getData());
            current.setBackground(null);
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);

            current.setImageURI(data.getData());
            current.setBackground(null);
        }
    }

    public void onImageViewClick(View view) {
        current = (ImageView) view;
        buildAlertDialog();
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
