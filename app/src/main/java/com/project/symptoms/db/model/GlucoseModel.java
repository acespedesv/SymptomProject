package com.project.symptoms.db.model;

public class GlucoseModel {
    private int id;
    private int value;
    private long date;
    private long time;

    public GlucoseModel(int id, int value, long date, long time) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.time = time;
    }

    public GlucoseModel(int value, long date, long time) {
        this.value = value;
        this.date = date;
        this.time = time;
    }

    public GlucoseModel(int value) {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTime() { return time; }

    public void setTime(long time) { this.time = time; }
}
