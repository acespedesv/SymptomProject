package com.project.symptoms.db.controller;

import android.content.Context;
import com.project.symptoms.db.dao.SymptomDaoImpl;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.util.DateTimeUtils;
import com.project.symptoms.util.NotificationWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SymptomController {

    private static SymptomController instance;
    private SymptomDaoImpl symptomDao;
    private Context context;

    private SymptomController(Context context){
        this.context = context;
        symptomDao = new SymptomDaoImpl(context);
    }

    public static SymptomController getInstance(Context context){
        if (instance == null) instance = new SymptomController(context);
        return instance;
    }

    // Return the id of the new row
    public long insert(float posX, float posY, float circleRadius, String date, String time, int circleSide){

        long newId = -1;
        try{
            Date startDate = DateTimeUtils.getInstance().joinDateAndTimeFromStrings(date,time);
            long endTime = -1;
            SymptomModel symptomModel = new SymptomModel(posX, posY, startDate.getTime(), endTime, circleRadius, circleSide);
            newId = symptomDao.insert(symptomModel);
            NotificationWrapper.getInstance(this.context).startReminderFor(newId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return newId;
    }

    public List<SymptomModel> listAll(){
        List<SymptomModel> result = new ArrayList<>();
        try{
            result = symptomDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<SymptomModel> listAll(long date, int circleSide){
        List<SymptomModel> result = new ArrayList<>();
        try{
            result = symptomDao.listAll(date, circleSide);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public SymptomModel findById(long symptomId){
        SymptomModel result = null;
        try{
            result = symptomDao.getById(symptomId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
