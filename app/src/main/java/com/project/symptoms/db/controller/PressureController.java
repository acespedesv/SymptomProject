package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.dao.PressureDao;
import com.project.symptoms.db.dao.PressureDaoImpl;
import com.project.symptoms.db.model.PressureModel;
import com.project.symptoms.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PressureController {

    private static PressureController instance;
    private static PressureDao pressureDao;

    private PressureController(){

    }

    public static PressureController getInstance(Context context){
        if(instance == null){
            instance = new PressureController();
            pressureDao = new PressureDaoImpl(new DBHelper(context));
        }
        return instance;
    }


    // Return the id of the new row
    public long insert(int systolic, int diastolic, String date, String time){
        long newId = -1;
        try{
            Date finalDate = DateTimeUtils.getInstance().getDateFromString(date);
            Date finalTime = DateTimeUtils.getInstance().getTimeFromString(time);
            PressureModel pressureModel = new PressureModel(systolic, diastolic, finalDate.getTime(), finalTime.getTime());
            newId = pressureDao.insert(pressureModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return newId;
    }

    // Return if whether succeeded or not
    public boolean delete(long pressureId){
        try {
            return pressureDao.delete(pressureId) != 1;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }

    }

    // Return if whether succeeded or not
    public boolean update(long pressureId, int systolic, int diastolic, long date, long time){
        try {
            PressureModel newPressureModel = new PressureModel(systolic, diastolic, date, time);
            return pressureDao.update(pressureId, newPressureModel) >= 1;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    public List<PressureModel> listAll(){
        List<PressureModel> result = new ArrayList<>();
        try{
            result = pressureDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }
}
