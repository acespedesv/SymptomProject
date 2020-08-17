package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.R;
import com.project.symptoms.db.dao.GlucoseLevelsDao;
import com.project.symptoms.db.dao.GlucoseLevelsDaoImpl;
import com.project.symptoms.db.model.GlucoseLevels;

import java.util.ArrayList;
import java.util.List;

public class GlucoseLevelsController {

    private static GlucoseLevelsController instance;
    private static GlucoseLevelsDao glucoseLevelsDao;
    private static Context context;

    private GlucoseLevelsController() {
    }

    public static GlucoseLevelsController getInstance(Context context) {
        if (instance == null) {
            instance = new GlucoseLevelsController();
            instance.context = context;
            glucoseLevelsDao = new GlucoseLevelsDaoImpl(context);
        }
        return instance;
    }

    public long insert(){
        try {
            String normalText = context.getString(R.string.glucose_level_normal);
            GlucoseLevels normal = new GlucoseLevels(normalText, "≤ 99", "≤ 139");
            glucoseLevelsDao.insert(normal);

            String prediabetesText = context.getString(R.string.glucose_level_prediabetes);
            GlucoseLevels preDiabetes = new GlucoseLevels(prediabetesText, "100 - 125", "140 - 199");
            glucoseLevelsDao.insert(preDiabetes);

            String diabetesText = context.getString(R.string.glucose_level_diabetes);
            GlucoseLevels diabetes = new GlucoseLevels(diabetesText, "≥ 126", "≥ 200");
            glucoseLevelsDao.insert(diabetes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<GlucoseLevels> listAll(){
        List<GlucoseLevels> result = new ArrayList<>();
        try{
            result = glucoseLevelsDao.listAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
