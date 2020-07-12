package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.BloodPressureLevelsDao;
import com.project.symptoms.db.dao.BloodPressureLevelsDaoImpl;
import com.project.symptoms.db.model.BloodPressureLevels;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureLevelsController {

    public static BloodPressureLevelsController instance;
    public static BloodPressureLevelsDao bloodPressureLevelsDao;

    public BloodPressureLevelsController() {
    }

    public static BloodPressureLevelsController getInstance(Context context){
        if(instance == null){
            instance = new BloodPressureLevelsController();
            bloodPressureLevelsDao = new BloodPressureLevelsDaoImpl(context);
        }
        return instance;
    }

    public long insert(){
        try {

            BloodPressureLevels normal = new BloodPressureLevels("Normal", 120, 90, 80, 60);
            bloodPressureLevelsDao.insert(normal);

            BloodPressureLevels elevated = new BloodPressureLevels("Elevada", 129, 120, 80, 60);
            bloodPressureLevelsDao.insert(elevated);

            BloodPressureLevels hypertensionStage1 = new BloodPressureLevels("Hipertension: Etapa 1", 139, 130, 90, 80);
            bloodPressureLevelsDao.insert(hypertensionStage1);

            BloodPressureLevels hypertensionStage2 = new BloodPressureLevels("Hipertension: Etapa 2", 180, 140, 120, 90);
            bloodPressureLevelsDao.insert(hypertensionStage2);

            BloodPressureLevels hypotension = new BloodPressureLevels("Hipotension", 90 , 40, 60, 40);
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
