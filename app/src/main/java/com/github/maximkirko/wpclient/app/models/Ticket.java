package com.github.maximkirko.wpclient.app.models;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Pavel on 25.09.2016.
 */
public class Ticket {
    private List<File> violationPhotos;
    private List<File> licensePlatePhotos;
    private Coords location;
    private Date date;
    private Enum violation;

    public List<File> getViolationPhotos() {
       return this.violationPhotos;
    }

    public void setViolationPhotos(List<File> violationPhotos) {
        this.violationPhotos = violationPhotos;
    }

    public List<File> getLicensePlatePhotos() {
        return this.licensePlatePhotos;
    }

    public void setLicensePlatePhotos(List<File> licensePlatePhotos){
        this.licensePlatePhotos = licensePlatePhotos;
    }

    public Coords getLocation(){
        return this.location;
    }

    public void setLocation(Coords location){
        this.location = location;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Enum getViolation(){
        return this.violation;
    }

    public void setViolation(Enum violation){
        this.violation = violation;
    }

    public Ticket() {

    }

    public Ticket(List<File> violationPhotos, List<File> licensePlatePhotos, Coords location, Date date, Enum violation){
        setViolationPhotos(violationPhotos);
        setLicensePlatePhotos(licensePlatePhotos);
        setLocation(location);
        setDate(date);
        setViolation(violation);
    }
}
