package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.GlucoseDao;
import com.project.symptoms.db.dao.GlucoseDaoImpl;
import com.project.symptoms.db.model.GlucoseModel;
import com.project.symptoms.util.DateTimeUtils;

import java.util.Date;

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
            Date completeDateTime = DateTimeUtils.getInstance().joinDateAndTimeFromStrings(dateText, hourText);
            GlucoseModel glucoseModel = new GlucoseModel(glucoseValue, completeDateTime.getTime());
            newId = glucoseDao.insert(glucoseModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newId;
    }

    public int delete(int id) {
        int quantity = -1;
        try {
            quantity = glucoseDao.delete(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public GlucoseModel select(int id) {
        GlucoseModel glucoseModel = null;
        try {
            glucoseModel = glucoseDao.select(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return glucoseModel;
    }

    public int update(int id, int glucoseValue, String dateText, String hourText) {
        int updatedId = -1;

        try {
            Date completeDateTime = DateTimeUtils.getInstance().joinDateAndTimeFromStrings(dateText, hourText);
            GlucoseModel glucoseModel = new GlucoseModel(id, glucoseValue, completeDateTime.getTime());
            updatedId = glucoseDao.update(glucoseModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedId;
    }
}
