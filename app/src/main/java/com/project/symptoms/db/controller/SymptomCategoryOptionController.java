package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.R;
import com.project.symptoms.db.dao.SymptomCategoryOptionDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;
import com.project.symptoms.util.SymptomCategoriesUtils;

import java.util.ArrayList;
import java.util.List;

public class SymptomCategoryOptionController {

    private static SymptomCategoryOptionController instance; // Todo: Fix warning
    private SymptomCategoryOptionDaoImpl symptomCategoryOptionDao;
    private SymptomCategoryController symptomCategoryController;
    private Context context;

    private SymptomCategoryOptionController(Context context){
        this.context = context;
        symptomCategoryController = SymptomCategoryController.getInstance(context);
        symptomCategoryOptionDao = new SymptomCategoryOptionDaoImpl(context);
    }

    public static SymptomCategoryOptionController getInstance(Context context){
        if (instance == null) instance = new SymptomCategoryOptionController(context);
        return instance;
    }

    // Return the id of the new row when inserting successfully
    public boolean insert(){
        boolean success = true;
        SymptomCategoryOptionModel symptomCategoryOptionModel;
        SymptomCategoryModel symptomCategoryModel;
        String[] categoryNames = SymptomCategoriesUtils.getCategoryNames(context.getResources());
        try{



        }catch (Exception e){
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    public List<SymptomCategoryOptionModel> listAll(){
        List<SymptomCategoryOptionModel> result = new ArrayList<>();
        try{
            result = symptomCategoryOptionDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public SymptomCategoryOptionModel getSymptomCategoryByName(String name) {
        SymptomCategoryOptionModel result = null;
        try{
            result = symptomCategoryOptionDao.selectSymptomCategoryOption(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
