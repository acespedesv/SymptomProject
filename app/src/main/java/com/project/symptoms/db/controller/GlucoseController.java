package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.GlucoseDao;
import com.project.symptoms.db.dao.GlucoseDaoImpl;
import com.project.symptoms.db.model.GlucoseModel;
import com.project.symptoms.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GlucoseController {

    private static GlucoseController instance;
    private static GlucoseDao glucoseDao;

    private GlucoseController() {
    }

    public static GlucoseController getInstance(Context context) {
        if (instance == null) {
            instance = new GlucoseController();
            glucoseDao = new GlucoseDaoImpl(context);
        }
        return instance;
    }

    public long insert(int glucoseValue, String dateText, String hourText) {
        long newId = -1;

        try {
            Date finalDate = DateTimeUtils.getInstance().getDateFromString(dateText);
            Date finalTime = DateTimeUtils.getInstance().getTimeFromString(hourText);
            GlucoseModel glucoseModel = new GlucoseModel(glucoseValue, finalDate.getTime(), finalTime.getTime());
            newId = glucoseDao.insert(glucoseModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newId;
    }

    public int delete(long id) {
        int quantity = -1;
        try {
            quantity = glucoseDao.delete(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public GlucoseModel select(long id) {
        GlucoseModel glucoseModel = null;
        try {
            glucoseModel = glucoseDao.select(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return glucoseModel;
    }

    public int update(long id, int glucoseValue, String dateText, String hourText) {
        //TODO validate ID
        int updatedRows = -1;
        try {
            Date date = DateTimeUtils.getInstance().getDateFromString(dateText);
            Date time = DateTimeUtils.getInstance().getTimeFromString(hourText);
            GlucoseModel glucoseModel = new GlucoseModel(id, glucoseValue, date.getTime(), time.getTime());
            glucoseDao.update(glucoseModel);
            updatedRows = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedRows;
    }

    public List<GlucoseModel> listAll(){
        List<GlucoseModel> result = null;
        try {
            result = glucoseDao.listAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
