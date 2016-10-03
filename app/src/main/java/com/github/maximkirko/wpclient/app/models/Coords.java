package com.github.maximkirko.wpclient.app.models;

/**
 * Created by Pavel on 25.09.2016.
 */
public class Coords {
    private double x;
    private double y;

    public Coords() {
        x = 0.00;
        y = 0.00;
    }

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double X() {
        return this.x;
    }

    public double Y() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.X() + " " + this.Y();
    }

    public static Coords getCoordsFromString(String line) {
        String[] mas = line.split(",");
        Coords coords = new Coords();
        coords.x = Double.parseDouble(mas[0]);
        coords.y = Double.parseDouble(mas[1]);
        return coords;
    }
}
