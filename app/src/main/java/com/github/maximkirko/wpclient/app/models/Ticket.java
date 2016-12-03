package com.github.maximkirko.wpclient.app.models;


import java.util.Date;
import java.util.Set;

/**
 * Created by Pavel on 25.09.2016.
 */
public class Ticket {

    private Long id;
    private Set<Photo> violationPhotos;
    private ViolationEnum violation;
    private String licensePlate;
    private String address;
    private String location;
    private Date date;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Photo> getViolationPhotos() {
        return violationPhotos;
    }

    public void setViolationPhotos(Set<Photo> violationPhotos) {
        this.violationPhotos = violationPhotos;
    }

    public ViolationEnum getViolation() {
        return violation;
    }

    public void setViolation(ViolationEnum violation) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", violationPhotos=" + violationPhotos +
                ", violation=" + violation +
                ", licensePlate='" + licensePlate + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Ticket() {

    }

}
