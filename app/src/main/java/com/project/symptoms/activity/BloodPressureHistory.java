package com.project.symptoms.activity;

import android.content.Intent;

import com.project.symptoms.R;
import com.project.symptoms.adapter.BloodPressureAdapter;
import com.project.symptoms.db.controller.PressureController;
import com.project.symptoms.util.DateTimeUtils;
import java.util.List;

import static com.project.symptoms.activity.BloodPressureForm.ARGUMENT_KEY_PRESSURE_ID;

public class BloodPressureHistory extends HistoryBase {
    @Override
    public void onDelete(long id) {
        PressureController.getInstance(this).delete(id);
        fetchModels();
    }

    @Override
    public void onEdit(long id) {
        Intent intent = new Intent(this, BloodPressureForm.class);
        intent.putExtra(ARGUMENT_KEY_PRESSURE_ID, id);
        startActivity(intent);
    }

    @Override
    public String[] getColumnsHeaders() {
        return new String[]{
                getString(R.string.pressure_measure_systolic),
                getString(R.string.pressure_measure_diastolic),
                getString(R.string.column_heading_date),
                getString(R.string.column_heading_time)
        };
    }

    @Override
    public void fetchModels() {
        List models = PressureController.getInstance(this).listAll();
        listView.setAdapter(new BloodPressureAdapter(models, DateTimeUtils.getInstance()));
    }

    @Override
    public int getTitleResourceId() {
        return R.string.pressure_history_title;
    }
}
