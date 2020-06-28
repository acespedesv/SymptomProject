package com.project.symptoms.db.model;

public class GlucoseModel {
    private int id;
    private int value;
    private long datetime;

    public GlucoseModel(int id, int value, long datetime) {
        this.id = id;
        this.value = value;
        this.datetime = datetime;
    }

    public GlucoseModel() {
    }

    public GlucoseModel(int value, long datetime) {
        this.value = value;
        this.datetime = datetime;
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

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
