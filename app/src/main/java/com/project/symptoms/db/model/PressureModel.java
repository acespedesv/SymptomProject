package com.project.symptoms.db.model;

public class PressureModel {

    private int pressure_id;
    private int systolic;
    private int diastolic;
    private long date;
    private long time;

    public PressureModel(int pressure_id, int systolic, int diastolic, long date, long time){
        this.pressure_id = pressure_id;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.date = date;
        this.time = time;
    }

    public PressureModel(int systolic, int diastolic, long date, long time){
        this(0, systolic, diastolic, date, time);
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

    public long getDate() {
        return date;
    }

    public void setDate(long datetime) {
        this.date = datetime;
    }

    public long getTime() { return time; }

    public void setTime(long time) { this.time = time; }
}
