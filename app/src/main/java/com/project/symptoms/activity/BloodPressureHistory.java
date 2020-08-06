package com.project.symptoms.activity;

import android.content.Intent;

import com.project.symptoms.R;
import com.project.symptoms.adapter.BloodPressureAdapter;
import com.project.symptoms.db.controller.PressureController;
import com.project.symptoms.util.DateTimeUtils;
import java.util.List;


public class BloodPressureHistory extends HistoryBase {
    @Override
    public void onDelete(long id) {
        try {
            PressureController.getInstance(this).delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fetchModels();
    }

    @Override
    public void onEdit(long id) {
        Intent intent = new Intent(this, BloodPressureForm.class);
        intent.putExtra(getString(R.string.intent_key_pressure_id), id);
        startActivity(intent);
    }

    @Override
    public String[] getColumnsHeaders() {
        return new String[]{
                getString(R.string.pressure_measure_systolic),
                getString(R.string.pressure_measure_diastolic),
                getString(R.string.date),
                getString(R.string.time)
        };
    }

    @Override
    public void fetchModels() {
        List models = null;
        try {
            models = PressureController.getInstance(this).listAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listView.setAdapter(new BloodPressureAdapter(models, DateTimeUtils.getInstance()));
    }

    @Override
    public int getTitleResourceId() {
        return R.string.pressure_history_title;
    }
}
