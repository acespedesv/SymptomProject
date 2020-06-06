package com.project.symptoms.Controller;

import android.content.Context;

import com.project.symptoms.DAO.GlucoseDao;
import com.project.symptoms.DAO.GlucoseDaoImpl;
import com.project.symptoms.Model.Glucose;
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
        Date date = null, hour = null;
        try {
            date = DateTimeUtils.getInstance().DATE_FORMATTER.parse(dateText);
            hour = DateTimeUtils.getInstance().TIME_FORMATTER.parse(hourText);
        }catch (Exception e){
            e.printStackTrace();
        }

        // Join the the date and time in a single Date object
        Date wholeDatetime = new Date(date.getTime());
        wholeDatetime.setHours(hour.getHours());
        wholeDatetime.setMinutes(hour.getMinutes());

        //Creates the model
        Glucose glucose = new Glucose(glucoseValue, wholeDatetime.getTime());

        try {
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
