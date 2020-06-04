package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.PressureDao;
import com.project.symptoms.db.dao.PressureDaoImpl;
import com.project.symptoms.db.model.Pressure;

import java.util.ArrayList;
import java.util.List;

public class PressureController {

    private static PressureController instance;
    private static PressureDao pressureDao;

    private PressureController(){

    }

    public static PressureController getInstance(Context context){
        if(instance == null){
            instance = new PressureController();
            pressureDao = new PressureDaoImpl(context);
        }
        return instance;
    }


    // Return the id of the new row
    public long insert(int systolic, int diastolic, long datetime){
        Pressure pressure = new Pressure(systolic, diastolic, datetime);
        long newId = -1;
        try{
            newId = pressureDao.insert(pressure);
        }catch (Exception e){
            e.printStackTrace();
        }
        return newId;
    }

    // Return if whether succeeded or not
    public boolean delete(long pressureId){
        try {
            pressureDao.delete(pressureId);
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }

    }

    // Return if whether succeeded or not
    public boolean update(long pressureId, int systolic, int diastolic, long datetime){
        try {
            Pressure newPressure = new Pressure(systolic, diastolic, datetime);
            pressureDao.update(pressureId, newPressure);
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    public List<Pressure> listAll(){
        List<Pressure> result = new ArrayList<>();
        try{
            result = pressureDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }
}
