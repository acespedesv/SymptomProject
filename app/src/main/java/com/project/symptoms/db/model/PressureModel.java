package com.project.symptoms.db.model;

public class PressureModel {

    private long pressureId;
    private int systolic;
    private int diastolic;
    private long date;
    private long time;

    public PressureModel(long pressureId, int systolic, int diastolic, long date, long time){
        this.pressureId = pressureId;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.date = date;
        this.time = time;
    }

    public PressureModel(int systolic, int diastolic, long date, long time){
        this(-1, systolic, diastolic, date, time);
    }


    public long getId() {
        return pressureId;
    }

    public void setId(long pressureId) {
        this.pressureId = pressureId;
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
