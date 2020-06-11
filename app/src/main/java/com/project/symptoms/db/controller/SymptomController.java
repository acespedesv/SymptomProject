package com.project.symptoms.db.controller;

import android.content.Context;
import com.project.symptoms.db.dao.SymptomDaoImpl;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.util.DateTimeUtils;

import java.util.Date;

public class SymptomController {

    private static SymptomController instance;
    private SymptomDaoImpl symptomDao;

    private SymptomController(Context context){
        symptomDao = new SymptomDaoImpl(context);
    }

    public static SymptomController getInstance(Context context){
        if (instance == null) instance = new SymptomController(context);
        return instance;
    }

    // Return the id of the new row
    public long insert(float posX, float posY, float circleRadius, String date, String time){

        long newId = -1;
        try{
            Date completeDatetime = DateTimeUtils.getInstance().joinDateAndTimeFromStrings(date, time);
            SymptomModel symptomModel = new SymptomModel(posX, posY, completeDatetime.getTime(), circleRadius);
            newId = symptomDao.insert(symptomModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return newId;
    }

}
