package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.SelectedCategoryOptionDaoImpl;
import com.project.symptoms.db.model.SelectedCategoryOptionModel;

import java.util.ArrayList;
import java.util.List;

public class SelectedCategoryOptionController {

    private static SelectedCategoryOptionController instance;
    private static SelectedCategoryOptionDaoImpl selectedCategoryOptionDao;

    private SelectedCategoryOptionController(){}

    public static SelectedCategoryOptionController getInstance(Context context){
        if (instance == null) {
            instance = new SelectedCategoryOptionController();
            selectedCategoryOptionDao = new SelectedCategoryOptionDaoImpl(context);
        }
        return instance;
    }

    public long insert(long symptomId, long categoryId){
        long newId = -1;
        try { newId = selectedCategoryOptionDao.insert(new SelectedCategoryOptionModel(symptomId, categoryId)); }
        catch (Exception e){ e.printStackTrace(); }
        return newId;
    }

    public boolean delete(long symptomId, long categoryId){
        try {
            selectedCategoryOptionDao.delete(symptomId, categoryId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SelectedCategoryOptionModel> getAll(){
        List<SelectedCategoryOptionModel> result = new ArrayList<>();
        try{
            result = selectedCategoryOptionDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<SelectedCategoryOptionModel> getAllBySymptom(long symptomId){
        List<SelectedCategoryOptionModel> result = new ArrayList<>();
        try{
            result = selectedCategoryOptionDao.listAllBySymptom(symptomId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteAllBySymptom(long symptomId){
        try {
            selectedCategoryOptionDao.deleteAllBySymptom(symptomId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
