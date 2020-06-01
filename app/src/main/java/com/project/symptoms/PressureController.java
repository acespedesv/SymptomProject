package com.project.symptoms;

public class PressureController {


    // Return the id of the new row
    public int insert(int systolic, int diastolic, String date, String time){
        Pressure pressure = new Pressure(systolic, diastolic, date, time);
        // int newId = PressureDao.insert(pressure);
        // return newId;
        return 0;
    }

    public int delete(int pressureId){
        return 0;
    }

}
