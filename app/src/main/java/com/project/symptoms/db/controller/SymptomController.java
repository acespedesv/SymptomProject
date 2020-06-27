package com.project.symptoms.db.controller;

import android.content.Context;
import com.project.symptoms.db.dao.SymptomDaoImpl;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    // Return the id of the new row when inserting successfully
    public long insert(int symptomId, float circlePosX, float circlePosY, String startDate, String startTime,
                       String endDate, String endTime, String description, String intensity,
                       String causingDrug, String causingFood, int intermittence, float circleRadius, int circleSide){

        long newId = -1;
        try{
            Date finalStartDate = DateTimeUtils.getInstance().getDateFromString(startDate);
            Date finalStartTime = DateTimeUtils.getInstance().getTimeFromString(startTime);
            Date finalEndDate = DateTimeUtils.getInstance().getDateFromString(endDate);
            Date finalEndTime = DateTimeUtils.getInstance().getTimeFromString(endTime);
            SymptomModel symptomModel = new SymptomModel(symptomId, circlePosX, circlePosY, finalStartDate.getTime(), finalStartTime.getTime(),
                    finalEndDate.getTime(), finalEndTime.getTime(), description,
                    intensity, causingDrug, causingFood, intermittence, circleRadius, circleSide);
            newId = symptomDao.insert(symptomModel);
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

}
