package com.github.maximkirko.wpclient.utils;

import android.util.Log;

import com.github.maximkirko.wpclient.app.models.Coords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Max on 13.04.2016.
 */
public class NetworkInfo {
    private static String correcter(String line) {
        line = line.trim();
        line = line.replaceAll("\\s{1,}", "\u0020");
        return line;
    }

    public static Coords getPlaceCoords(String placeName) throws IOException {
        Coords result = new Coords();
        placeName = correcter(placeName);
        placeName.replace(" ", "%20");

        try {
            URL url = new URL("https://geocode-maps.yandex.ru/1.x/?geocode=" + "Гродно, " + placeName);

            try {
                BufferedReader reader = new BufferedReader (new InputStreamReader(url.openStream(), "UTF-8"));
                String str = reader.readLine();
                while (str != null) {
                    str = reader.readLine();
                    if(str.contains("<AddressLine>")) {
                        try {
                            String[] mas;
                            mas = str.split("<|>");
                            if (mas[2].equals("Гродно")) {
                                return null;
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    if(str.contains("<pos>")) {
                        try {
                            String[] mas;
                            mas = str.split("<|>");
                            mas = mas[2].split(" ");
                            result.setY(Double.parseDouble(mas[0]));
                            result.setX(Double.parseDouble(mas[1]));
                        }
                        catch(Exception ex) {
                            return result;
                        }
                        break;
                    }
                }
                reader.close();
            } catch (IOException e) {
                return result;
            }
        }
        catch (MalformedURLException ex) {
            return result;
        }
        return result;
    }

    public static String getPlaceName(Coords coords) throws IOException {
        String result = "";
        double temp = coords.X();
        coords.setX(coords.Y());
        coords.setY(temp);

        String[] mas;

        try {
            URL url = new URL("https://geocode-maps.yandex.ru/1.x/?geocode=" + coords.X() + "%20" + coords.Y());
            //result = url.getContent().toString();
            try {
                BufferedReader reader = new BufferedReader (new InputStreamReader(url.openStream(), "UTF-8"));
                String str = reader.readLine();
                while (str != null) {
                    System.out.println(str);
                    str = reader.readLine();
                    if(str != null) {
                        if(str.contains("<AddressLine>")) {
                            try {
                                mas = str.split("<|>");
                                result = mas[2];
                            }
                            catch(Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        }
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String getPlaceNameFromString(String line) throws IOException {
        String result = "Не найдено!";

        Coords coords = new Coords();
        String[] mas;
        mas = line.split(" ");
        coords.setY(Double.parseDouble(mas[0]));
        coords.setX(Double.parseDouble(mas[1]));

        return getPlaceName(coords);
    }
}
