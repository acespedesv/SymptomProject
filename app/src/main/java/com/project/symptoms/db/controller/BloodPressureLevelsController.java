package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.R;
import com.project.symptoms.db.dao.BloodPressureLevelsDao;
import com.project.symptoms.db.dao.BloodPressureLevelsDaoImpl;
import com.project.symptoms.db.model.BloodPressureLevels;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureLevelsController {

    public static BloodPressureLevelsController instance;
    public static BloodPressureLevelsDao bloodPressureLevelsDao;
    private static Context context;

    public BloodPressureLevelsController() {
    }

    public static BloodPressureLevelsController getInstance(Context context){
        if(instance == null){
            instance = new BloodPressureLevelsController();
            instance.context = context;
            bloodPressureLevelsDao = new BloodPressureLevelsDaoImpl(context);
        }
        return instance;
    }

    public long insert(){
        try {

            String normalCategory = context.getString(R.string.blood_pressure_category_normal);
            BloodPressureLevels normal = new BloodPressureLevels(normalCategory, 120, 90, 80, 60);
            bloodPressureLevelsDao.insert(normal);

            String elevatedCategory = context.getString(R.string.blood_pressure_category_elevated);
            BloodPressureLevels elevated = new BloodPressureLevels(elevatedCategory, 129, 120, 80, 60);
            bloodPressureLevelsDao.insert(elevated);

            String hypertensionStage1Category = context.getString(R.string.blood_pressure_category_hypertension_stage_1);
            BloodPressureLevels hypertensionStage1 = new BloodPressureLevels(hypertensionStage1Category, 139, 130, 90, 80);
            bloodPressureLevelsDao.insert(hypertensionStage1);

            String hypertensionStage2Category = context.getString(R.string.blood_pressure_category_hypertension_stage_2);
            BloodPressureLevels hypertensionStage2 = new BloodPressureLevels(hypertensionStage2Category, 180, 140, 120, 90);
            bloodPressureLevelsDao.insert(hypertensionStage2);

            String hypotensionCategory = context.getString(R.string.blood_pressure_category_hypotension);
            BloodPressureLevels hypotension = new BloodPressureLevels(hypotensionCategory, 90 , 40, 60, 40);
            bloodPressureLevelsDao.insert(hypotension);

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<BloodPressureLevels> listAll(){
        List<BloodPressureLevels> result = new ArrayList<>();
        try{
            result = bloodPressureLevelsDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
