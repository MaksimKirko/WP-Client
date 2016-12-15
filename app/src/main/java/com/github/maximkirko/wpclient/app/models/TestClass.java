package com.github.maximkirko.wpclient.app.models;

/**
 * Created by MadMax on 15.12.2016.
 */
public class TestClass {

    private String text;
    private int number;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "text='" + text + '\'' +
                ", number=" + number +
                '}';
    }
}
