package com.project.symptoms.Model;

import android.widget.ScrollView;

import java.sql.Time;
import java.util.Date;

public class Glucose {
    private int id;
    private int value;
    private String date;
    private String time;

    public Glucose() {
    }

    public Glucose(int value, String date, String time) {
        this.value = value;
        this.date = date;
        this.time = time;
    }

    public Glucose(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
