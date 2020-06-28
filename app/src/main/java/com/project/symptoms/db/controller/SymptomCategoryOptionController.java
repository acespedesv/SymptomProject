package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.SymptomCategoryOptionDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;
import com.project.symptoms.util.SymptomCategoriesUtils;

import java.util.ArrayList;
import java.util.HashMap;
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

        // Prepare data for insertion
        String[] categoryNames = SymptomCategoriesUtils.getCategoryNames(context.getResources());
        String[] painTypeNames = SymptomCategoriesUtils.getPainTypeNames(context.getResources());
        String[] digestiveNames = SymptomCategoriesUtils.getDigestiveTypeNames(context.getResources());
        String[] respiratoryNames = SymptomCategoriesUtils.getRespiratoryTypeNames(context.getResources());
        String[] sensoryNames = SymptomCategoriesUtils.getSensoryTypeNames(context.getResources());
        String[] emotionalNames = SymptomCategoriesUtils.getEmotionalTypeNames(context.getResources());
        String[] dermatologicalNames = SymptomCategoriesUtils.getDermatologicalTypeNames(context.getResources());
        String[] triggeringActivitiesNames = SymptomCategoriesUtils.getTriggeringActivityTypeNames(context.getResources());
        String[] triggeringEmotionsNames = SymptomCategoriesUtils.getTriggeringEmotionTypeNames(context.getResources());
        String[] triggeringWeatherStateNames = SymptomCategoriesUtils.getTriggeringWeatherStateTypeNames(context.getResources());
        String[][] namesMatrix = new String[][]{painTypeNames, digestiveNames, respiratoryNames, sensoryNames, emotionalNames, dermatologicalNames, triggeringActivitiesNames,
        triggeringEmotionsNames, triggeringWeatherStateNames};
        HashMap<String, String[]> namesHashMap = new HashMap<>();
        int matrixIndex = 0;
        for (String name: categoryNames) { namesHashMap.put(name, namesMatrix[matrixIndex++]); }
        
        try{
            for (String categoryName : namesHashMap.keySet()) {
                symptomCategoryModel = symptomCategoryController.getSymptomCategoryByName(categoryName); // Get model to use it's PK as FK
                int fk = symptomCategoryModel.getCategoryId();
                for (String optionName: namesHashMap.get(categoryName)) {
                    symptomCategoryOptionModel = new SymptomCategoryOptionModel(fk, optionName);
                    symptomCategoryOptionDao.insert(symptomCategoryOptionModel);
                }
            }
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
