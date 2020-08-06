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

    // Return the id of the new row when inserting successfully
    public long insert(float circlePosX, float circlePosY, String startDate, String startTime,
                       int duration, String description, String intensity,
                       String causingDrug, String causingFood, int intermittence, float circleRadius, int circleSide)
                       throws  Exception{

        long newId = -1;
        Date finalStartDate = DateTimeUtils.getInstance().getDateFromString(startDate);
        Date finalStartTime = DateTimeUtils.getInstance().getTimeFromString(startTime);
        SymptomModel symptomModel = new SymptomModel(circlePosX, circlePosY, finalStartDate.getTime(), finalStartTime.getTime(),
                duration, description, intensity, causingDrug, causingFood, intermittence, circleRadius, circleSide);
        newId = symptomDao.insert(symptomModel);
        if(symptomModel.getDuration() < 0){
            NotificationWrapper.getInstance(this.context).startReminderFor(newId);
            NotificationWrapper.getInstance(this.context).showReminderSetToast();

        }
        return newId;
    }

    public List<SymptomModel> listAll() throws Exception{
        return symptomDao.selectAll();
    }

    public List<SymptomModel> listAll(long date, int circleSide) throws Exception{
        return symptomDao.selectAll(date, circleSide);
    }

    public SymptomModel findById(long symptomId) throws Exception{
        return symptomDao.select(symptomId);
    }

    public int updateSymptom(SymptomModel symptomModel) throws Exception{
        return symptomDao.update(symptomModel);
    }

    public int deleteSymptomById(long symptomId) throws Exception{
        return symptomDao.delete(symptomId);
    }

}
