package com.project.symptoms.db.model;

public class Glucose {
    private int id;
    private int value;
    private long datetime;

    public Glucose() {
    }

    public Glucose(int value, long datetime) {
        this.value = value;
        this.datetime = datetime;
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

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
