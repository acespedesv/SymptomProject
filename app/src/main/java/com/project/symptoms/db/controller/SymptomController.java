package com.project.symptoms.db.controller;

import android.app.Notification;
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
            if(symptomModel.getDuration() < 0){
                NotificationWrapper.getInstance(this.context).startReminderFor(newId);
                NotificationWrapper.getInstance(this.context).showReminderSetToast();
            }
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

    public boolean updateSymptom(long id, SymptomModel symptomModel){
        try {
            return symptomDao.update(id, symptomModel);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSymptomById(long symptomId){
        try {
            symptomDao.delete(symptomId);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
