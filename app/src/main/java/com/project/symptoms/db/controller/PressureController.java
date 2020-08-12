package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.dao.PressureDao;
import com.project.symptoms.db.dao.PressureDaoImpl;
import com.project.symptoms.db.dao.SymptomDao;
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
            pressureDao = new PressureDaoImpl(context);
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

    // Return if whether succeeded or not TODO generalize this
    public long update(long pressureId, int systolic, int diastolic, String date, String time){
        long result = -1;
        try {
            Date dateDate = DateTimeUtils.getInstance().DATE_FORMATTER.parse(date);
            Date timeDate = DateTimeUtils.getInstance().TIME_FORMATTER.parse(time);
            PressureModel newPressureModel = new PressureModel(pressureId,
                    systolic, diastolic, dateDate.getTime(), timeDate.getTime());
            result = pressureDao.update(newPressureModel) == 1 ? 1 : -1;
        }catch (Exception e ){
            e.printStackTrace();
        }
        return result;
    }

    public List<PressureModel> selectAll(){
        List<PressureModel> result = new ArrayList<>();
        try{
            result = pressureDao.selectAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<PressureModel> select(long initialDate, long finalDate) {
        List<PressureModel> result = null;
        try {
            result = pressureDao.select(initialDate, finalDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public PressureModel select(long id){
        PressureModel result = null;
        try{
            result = pressureDao.select(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
