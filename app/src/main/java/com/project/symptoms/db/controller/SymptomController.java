package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.SymptomDao;
import com.project.symptoms.db.dao.SymptomDaoImpl;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.util.DateTimeUtils;
import com.project.symptoms.util.NotificationWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SymptomController {

    private static SymptomController instance;
    private SymptomDao symptomDao;
    private Context context;

    private SymptomController(Context context){
        this.context = context;
        symptomDao = new SymptomDaoImpl(context);
    }

    public static SymptomController getInstance(Context context){
        if (instance == null) instance = new SymptomController(context);
        return instance;
    }

    // Return the id of the new row when inserting successfully
    public long insert(float circlePosX, float circlePosY, String startDate, String startTime,
                       int duration, String description, String intensity,
                       String causingDrug, String causingFood, int intermittence, float circleRadius, int circleSide){

        long newId = -1;
        try{
            Date finalStartDate = DateTimeUtils.getInstance().getDateFromString(startDate);
            Date finalStartTime = DateTimeUtils.getInstance().getTimeFromString(startTime);

            SymptomModel symptomModel = new SymptomModel();
            symptomModel.setCirclePosX(circlePosX);
            symptomModel.setCirclePosY(circlePosY);
            symptomModel.setCircleRadius(circleRadius);
            symptomModel.setCircleSide(circleSide);
            symptomModel.setStartDate(finalStartDate.getTime());
            symptomModel.setStartTime(finalStartTime.getTime());
            symptomModel.setDuration(duration);
            symptomModel.setIntensity(intensity);
            symptomModel.setDescription(description);
            symptomModel.setCausingDrug(causingDrug);
            symptomModel.setCausingFood(causingFood);
            symptomModel.setIntermittence(intermittence);

            newId = symptomDao.insert(symptomModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return newId;
    }

    public List<SymptomModel> selectAll(){
        List<SymptomModel> result = new ArrayList<>();
        try{
            result = symptomDao.selectAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<SymptomModel> selectAllByDateAndSide(long date, int circleSide){
        List<SymptomModel> result = new ArrayList<>();
        try{
            result = symptomDao.selectAllByDateAndSide(date, circleSide);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public SymptomModel select(long symptomId){
        SymptomModel result = null;
        try{
            result = symptomDao.select(symptomId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int update(SymptomModel symptomModel){
        int rowsUpdated = -1;
        try {
            rowsUpdated = symptomDao.update(symptomModel);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    public int delete(long symptomId){
        int rowsDeleted = -1;
        try {
            rowsDeleted = symptomDao.delete(symptomId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rowsDeleted;
    }

    public void setGlucoseDao(SymptomDaoImpl symptomDao) {
        this.symptomDao = symptomDao;
    }
}
