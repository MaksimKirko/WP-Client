package com.github.maximkirko.wpclient.app.models;

import android.widget.ImageView;

import com.github.maximkirko.wpclient.app.models.violations.Violations;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Pavel on 25.09.2016.
 */
public class Ticket {
    private List<byte[]> violationPhotos;
    private Violations violation;
    private String licensePlate;
    private String address;
    private Coords location;
    private Date date;
    private String comment;

    public Enum getViolation() {
        return violation;
    }

    public void setViolation(Violations violation) {
        this.violation = violation;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coords getLocation() {
        return location;
    }

    public void setLocation(Coords location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<byte[]> getViolationPhotos() {
        return violationPhotos;
    }

    public void setViolationPhotos(List<byte[]> violationPhotos) {
        this.violationPhotos = violationPhotos;
    }

    public Ticket() {

    }

    public Ticket(List<byte[]> violationPhotos, Violations violation, String licensePlate, String address, Coords location, Date date, String comment) {
        this.violationPhotos = violationPhotos;
        this.violation = violation;
        this.licensePlate = licensePlate;
        this.address = address;
        this.location = location;
        this.date = date;
        this.comment = comment;
    }
}
