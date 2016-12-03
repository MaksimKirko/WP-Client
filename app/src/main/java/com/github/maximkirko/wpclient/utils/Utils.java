package com.github.maximkirko.wpclient.utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.maximkirko.wpclient.app.models.Coords;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by MadMax on 15.10.2016.
 */
public class Utils {
    public static byte[] imageToByteArray(ImageView image) {
        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache();

        Bitmap bm = image.getDrawingCache();

        //convert Bitmap* into ByteArray

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    private static String loc = "";
    private static EditText viewLoc;
    private static EditText viewDate;

    public static void setLocation(EditText et) {
        viewLoc = et;
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Coords myCoords = null;
                    do {
                        myCoords = new Coords(Geolocation.imHere.getLatitude(),
                                Geolocation.imHere.getLongitude());
                        loc = NetworkInfo.getPlaceName(myCoords);
                    }
                    while(myCoords.getX() == 0.0 || myCoords.getY() == 0.0);
                }
                catch (Exception ex) {
                }
                handlerMyLoc.sendEmptyMessage(0);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static Handler handlerMyLoc = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewLoc.setText(loc);
        }
    };

    public static void setDate(EditText et) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        viewDate = et;
        viewDate.setText(strDate);
    }
}
