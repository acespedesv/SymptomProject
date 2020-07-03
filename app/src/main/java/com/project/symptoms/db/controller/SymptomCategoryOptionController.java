package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.SymptomCategoryOptionDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;
import com.project.symptoms.dto.CategoryDTO;
import com.project.symptoms.dto.SymptomOptionDTO;
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
        boolean initialInsertionAlreadyDone = listAll().size() > 0;
        if(! initialInsertionAlreadyDone){
            HashMap<CategoryDTO, SymptomOptionDTO[]> initialHashMap = getInitialOptionsHashMap();

            SymptomCategoryOptionModel symptomCategoryOptionModel;
            SymptomCategoryModel symptomCategoryModel;

            try{

                for (CategoryDTO category : initialHashMap.keySet()) {
                    String categoryName = context.getResources().getString(category.nameStringId);
                    symptomCategoryModel = symptomCategoryController.getSymptomCategoryByName(categoryName); // Get model to use its PK as FK
                    int categoryId = symptomCategoryModel.getCategoryId();

                    for (SymptomOptionDTO option : initialHashMap.get(category)) {
                        String optionLabel = context.getResources().getString(option.labelStringId);
                        symptomCategoryOptionModel = new SymptomCategoryOptionModel(categoryId, optionLabel, option.resourceIconId);
                        symptomCategoryOptionDao.insert(symptomCategoryOptionModel);
                    }
                }
            }catch (Exception e){
                success = false;
                e.printStackTrace();
            }
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

    private HashMap<CategoryDTO, SymptomOptionDTO[]> getInitialOptionsHashMap(){
        // Prepare data for insertion
        CategoryDTO[] categories = SymptomCategoriesUtils.getCategoryNames();

        SymptomOptionDTO[] painTypeNames = SymptomCategoriesUtils.getPainTypeNames();
        SymptomOptionDTO[] digestiveNames = SymptomCategoriesUtils.getDigestiveTypeNames();
        SymptomOptionDTO[] respiratoryNames = SymptomCategoriesUtils.getRespiratoryTypeNames();
        SymptomOptionDTO[] sensoryNames = SymptomCategoriesUtils.getSensoryTypeNames();
        SymptomOptionDTO[] emotionalNames = SymptomCategoriesUtils.getEmotionalTypeNames();
        SymptomOptionDTO[] dermatologicalNames = SymptomCategoriesUtils.getDermatologicalTypeNames();
        SymptomOptionDTO[] triggeringActivitiesNames = SymptomCategoriesUtils.getTriggeringActivityTypeNames();
        SymptomOptionDTO[] triggeringEmotionsNames = SymptomCategoriesUtils.getTriggeringEmotionTypeNames();
        SymptomOptionDTO[] triggeringWeatherStateNames = SymptomCategoriesUtils.getTriggeringWeatherStateTypeNames();

        SymptomOptionDTO[][] namesMatrix = new SymptomOptionDTO[][]{painTypeNames, digestiveNames, respiratoryNames, sensoryNames, emotionalNames, dermatologicalNames, triggeringActivitiesNames,
                triggeringEmotionsNames, triggeringWeatherStateNames};

        HashMap<CategoryDTO, SymptomOptionDTO[]> namesHashMap = new HashMap<>();

        int matrixIndex = 0;
        for (CategoryDTO categoryDTO : categories) {
            namesHashMap.put(categoryDTO, namesMatrix[matrixIndex++]);
        }
        return namesHashMap;
    }

    public SymptomCategoryOptionModel getSymptomCategoryOptionByName(String name) {
        SymptomCategoryOptionModel result = null;
        try{
            result = symptomCategoryOptionDao.selectSymptomCategoryOption(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<SymptomCategoryOptionModel> listByCategory(int categoryId){
        List<SymptomCategoryOptionModel> all = listAll();
        List<SymptomCategoryOptionModel> result = new ArrayList<>();
        for(SymptomCategoryOptionModel each : all){
            if(each.getCategoryFkId() == categoryId){
                result.add(each);
            }
        }
        return result;
    }

}
