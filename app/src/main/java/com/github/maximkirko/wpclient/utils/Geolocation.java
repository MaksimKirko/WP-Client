package com.github.maximkirko.wpclient.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Geolocation implements LocationListener {

    public static Location imHere; // здесь будет всегда доступна самая последняя информация о местоположении пользователя.

    public static void SetUpLocationListener(Context context) // это нужно запустить в самом начале работы программы
    {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new Geolocation();

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100,
                10,
                locationListener); // здесь можно указать другие более подходящие вам параметры

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                10,
                locationListener); // здесь можно указать другие более подходящие вам параметры

        try {
            imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        catch (Exception ex) {  }

        if(imHere == null) {
            imHere = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }

    @Override
    public void onLocationChanged(Location loc) {
        imHere = loc;
    }
    @Override
    public void onProviderDisabled(String provider) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}