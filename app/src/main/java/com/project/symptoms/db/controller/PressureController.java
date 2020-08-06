package com.project.symptoms.db.controller;

import android.content.Context;

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
            pressureDao = new PressureDaoImpl(context);
        }
        return instance;
    }


    // Return the id of the new row
    public long insert(int systolic, int diastolic, String date, String time) throws Exception{
        Date finalDate = DateTimeUtils.getInstance().getDateFromString(date);
        Date finalTime = DateTimeUtils.getInstance().getTimeFromString(time);
        PressureModel pressureModel = new PressureModel(systolic, diastolic, finalDate.getTime(), finalTime.getTime());
        long newId = pressureDao.insert(pressureModel);
        return newId;
    }

    // Return if whether succeeded or not
    public int delete(long pressureId) throws Exception{
        return pressureDao.delete(pressureId);
    }

    // Return if whether succeeded or not TODO generalize this
    public int update(long pressureId, int systolic, int diastolic, String date, String time) throws Exception{
        Date dateDate = DateTimeUtils.getInstance().DATE_FORMATTER.parse(date);
        Date timeDate = DateTimeUtils.getInstance().TIME_FORMATTER.parse(time);
        PressureModel newPressureModel = new PressureModel(pressureId,
                systolic, diastolic, dateDate.getTime(), timeDate.getTime());
        int result = pressureDao.update(newPressureModel);
        return result;
    }

    public List<PressureModel> listAll() throws  Exception{
        return pressureDao.selectAll();
    }

    public PressureModel select(long id) throws Exception{
        return pressureDao.select(id);
    }
}
