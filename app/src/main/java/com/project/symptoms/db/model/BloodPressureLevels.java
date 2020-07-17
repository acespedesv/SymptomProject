package com.project.symptoms.db.model;

public class BloodPressureLevels {

    private int id;
    private String category;
    private int systolicMaximum;
    private int systolicMinimum;
    private int diastolicMaximum;
    private int diastolicMinimum;

    public BloodPressureLevels(String category, int systolicMaximum, int systolicMinimum, int diastolicMaximum, int diastolicMinimum) {
        this.category = category;
        this.systolicMaximum = systolicMaximum;
        this.systolicMinimum = systolicMinimum;
        this.diastolicMaximum = diastolicMaximum;
        this.diastolicMinimum = diastolicMinimum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSystolicMaximum() {
        return systolicMaximum;
    }

    public void setSystolicMaximum(int systolicMaximum) {
        this.systolicMaximum = systolicMaximum;
    }

    public int getSystolicMinimum() {
        return systolicMinimum;
    }

    public void setSystolicMinimum(int systolicMinimum) {
        this.systolicMinimum = systolicMinimum;
    }

    public int getDiastolicMaximum() {
        return diastolicMaximum;
    }

    public void setDiastolicMaximum(int diastolicMaximum) {
        this.diastolicMaximum = diastolicMaximum;
    }

    public int getDiastolicMinimum() {
        return diastolicMinimum;
    }

    public void setDiastolicMinimum(int diastolicMinimum) {
        this.diastolicMinimum = diastolicMinimum;
    }
}
