package com.project.symptoms.db.controller;

import android.content.Context;

import com.project.symptoms.db.dao.GlucoseLevelsDao;
import com.project.symptoms.db.dao.GlucoseLevelsDaoImpl;
import com.project.symptoms.db.model.GlucoseLevels;

import java.util.ArrayList;
import java.util.List;

public class GlucoseLevelsController {

    private static GlucoseLevelsController instance;
    private static GlucoseLevelsDao glucoseLevelsDao;

    private GlucoseLevelsController() {
    }

    public static GlucoseLevelsController getInstance(Context context) {
        if (instance == null) {
            instance = new GlucoseLevelsController();
            glucoseLevelsDao = new GlucoseLevelsDaoImpl(context);
        }
        return instance;
    }

    public long insert(){
        try {
            GlucoseLevels normal = new GlucoseLevels("Normal", "≤ 99", "≤ 139");
            glucoseLevelsDao.insert(normal);

            GlucoseLevels preDiabetes = new GlucoseLevels("PreDiabetes", "100 - 125", "140 - 199");
            glucoseLevelsDao.insert(preDiabetes);

            GlucoseLevels diabetes = new GlucoseLevels("Diabetes", "≥ 126", "≥ 200");
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
