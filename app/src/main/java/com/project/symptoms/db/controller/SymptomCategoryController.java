package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.R;
import com.project.symptoms.db.dao.SymptomCategoryDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;

public class SymptomCategoryController {

    private static SymptomCategoryController instance;
    private SymptomCategoryDaoImpl symptomCategoryDao;
    private Context context;

    private SymptomCategoryController(Context context){
        this.context = context;
        symptomCategoryDao = new SymptomCategoryDaoImpl(context);
    }

    public static SymptomCategoryController getInstance(Context context){
        if (instance == null) instance = new SymptomCategoryController(context);
        return instance;
    }

    // Return the id of the new row when inserting successfully
    public boolean insert(){
        boolean success = true;
        SymptomCategoryModel symptomCategoryModel = null;
        try{
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.pain_type));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.digestive_pain));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.respiratory_pain));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.sensory_pain));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.emotional_pain));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.dermatological_pain));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.triggering_activity));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.triggering_feeling));
            symptomCategoryDao.insert(symptomCategoryModel);
            symptomCategoryModel = new SymptomCategoryModel(context.getResources().getString(R.string.triggering_weather_condition));
            symptomCategoryDao.insert(symptomCategoryModel);
        }catch (Exception e){
            success = false;
            e.printStackTrace();
        }
        return success;
    }

}
