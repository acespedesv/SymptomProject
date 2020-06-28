package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.R;
import com.project.symptoms.db.dao.SymptomCategoryDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class SymptomCategoryController {

    private static SymptomCategoryController instance; // Todo: Fix warning
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
        SymptomCategoryModel symptomCategoryModel;
        String[] names = {context.getResources().getString(R.string.pain_type),
                context.getResources().getString(R.string.digestive_pain),
                context.getResources().getString(R.string.respiratory_pain),
                context.getResources().getString(R.string.sensory_pain),
                context.getResources().getString(R.string.emotional_pain),
                context.getResources().getString(R.string.dermatological_pain),
                context.getResources().getString(R.string.triggering_activity),
                context.getResources().getString(R.string.triggering_feeling),
                context.getResources().getString(R.string.triggering_weather_condition)};
        try{
            for (String name: names) {
                symptomCategoryModel = new SymptomCategoryModel(name);
                symptomCategoryDao.insert(symptomCategoryModel);
            }
        }catch (Exception e){
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    public List<SymptomCategoryModel> listAll(){
        List<SymptomCategoryModel> result = new ArrayList<>();
        try{
            result = symptomCategoryDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public SymptomCategoryModel getSymptomCategoryByName(String name) {
        SymptomCategoryModel result = null;
        try{
            result = symptomCategoryDao.selectSymptomCategory(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
