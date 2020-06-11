package com.project.symptoms.db.model;

public class PressureModel {

    private int pressure_id;
    private int systolic;
    private int diastolic;
    private long datetime;

    public PressureModel(int pressure_id, int systolic, int diastolic, long datetime){
        this.pressure_id = pressure_id;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.datetime = datetime;
    }

    public PressureModel(int systolic, int diastolic, long datetime){
        this(0, systolic, diastolic, datetime);
    }


    public int getPressure_id() {
        return pressure_id;
    }

    public void setPressure_id(int pressure_id) {
        this.pressure_id = pressure_id;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
