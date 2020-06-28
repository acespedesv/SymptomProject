package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.SymptomCategoryDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;

public class SymptomCategoryController {

    private static SymptomCategoryController instance;
    private SymptomCategoryDaoImpl symptomCategoryDao;

    private SymptomCategoryController(Context context){
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
            symptomCategoryModel = new SymptomCategoryModel("Tipo de Dolor");
            symptomCategoryDao.insert(symptomCategoryModel);

        }catch (Exception e){
            success = false;
            e.printStackTrace();
        }
        return success;
    }

}
