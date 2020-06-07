package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.GlucoseDao;
import com.project.symptoms.db.dao.GlucoseDaoImpl;
import com.project.symptoms.db.model.Glucose;
import com.project.symptoms.util.DateTimeUtils;

import java.util.Date;

public class GlucoseController {

    private static GlucoseController instance;
    private static GlucoseDao glucoseDao;

    private GlucoseController(){}

    public static GlucoseController getInstance(Context context){
        if(instance == null){
            instance = new GlucoseController();
            glucoseDao = new GlucoseDaoImpl(context);
        }
        return instance;
    }

    public long insert(int glucoseValue, String dateText, String hourText) {
        long newId = -1;

        try {
            Date completeDateTime = DateTimeUtils.getInstance().joinDateAndTimeFromStrings(dateText, hourText);
            Glucose glucose = new Glucose(glucoseValue, completeDateTime.getTime());
            newId = glucoseDao.insert(glucose);
        }catch (Exception e){
            e.printStackTrace();
        }
        return newId;
    }

    public int deleteData() {
        /*int id = 2;
        glucose = new Glucose(id);
        //Calls the dao
        glucoseDao = new GlucoseDaoImpl(context);
        return glucoseDao.delete(glucose.getId());*/
        return 2;
    }
}
