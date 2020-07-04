package com.project.symptoms.db.controller;

import android.content.Context;
import android.content.res.Resources;

import com.project.symptoms.R;
import com.project.symptoms.db.dao.SymptomCategoryDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;
import com.project.symptoms.dto.CategoryDTO;
import com.project.symptoms.util.SymptomCategoriesUtils;

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
        boolean initialInsertionAlreadyDone = listAll().size() > 0;
        if(! initialInsertionAlreadyDone){
            try {
                Resources res = context.getResources();
                SymptomCategoryModel symptomCategoryModel;
                CategoryDTO[] allCategories = SymptomCategoriesUtils.getCategoryNames();
                for (CategoryDTO categoryDTO : allCategories) {
                    String categoryName = res.getString(categoryDTO.nameStringId);
                    symptomCategoryModel = new SymptomCategoryModel(categoryName);
                    symptomCategoryDao.insert(symptomCategoryModel);
                }
            }catch (Exception e){
                success = false;
                e.printStackTrace();
            }
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
