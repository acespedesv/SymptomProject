package com.project.symptoms.activity;

import android.content.Intent;
import android.os.Bundle;

import com.project.symptoms.R;
import com.project.symptoms.adapter.BloodPressureAdapter;
import com.project.symptoms.db.controller.PressureController;
import com.project.symptoms.util.DateTimeUtils;
import java.util.List;


public class BloodPressureHistory extends HistoryBase {
    BloodPressureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        updateModelsAccordingToLimit();
        this.adapter = new BloodPressureAdapter(this.models, DateTimeUtils.getInstance());
        listView.setAdapter(adapter);
    }

    @Override
    public void onDelete(long id) {
        PressureController.getInstance(this).delete(id);
        updateRows();
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
    public void updateRows() {
        updateModelsAccordingToLimit();
        listView.setAdapter(new BloodPressureAdapter(this.models, DateTimeUtils.getInstance()));
    }

    @Override
    public void onLoadMoreClicked() {
        maxRowsToShow += pageSize;
        int previousValue = models.size();
        updateModelsAccordingToLimit();
        if(previousValue == models.size())
            hideLoadMoreButton();
        else
            updateRows();
    }

    @Override
    public int getTitleResourceId() {
        return R.string.pressure_history_title;
    }


    @Override
    public List getAllModels() {
        return PressureController.getInstance(this).selectAll();
    }
}
