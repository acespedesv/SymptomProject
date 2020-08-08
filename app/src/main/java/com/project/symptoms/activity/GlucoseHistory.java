package com.project.symptoms.activity;

import android.content.Intent;
import android.os.Bundle;

import com.project.symptoms.R;
import com.project.symptoms.adapter.GlucoseAdapter;
import com.project.symptoms.db.controller.GlucoseController;
import com.project.symptoms.util.DateTimeUtils;

import java.util.List;

public class GlucoseHistory extends HistoryBase {
    GlucoseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        updateModelsAccordingToLimit();
        this.adapter = new GlucoseAdapter(this.models, DateTimeUtils.getInstance());
        listView.setAdapter(adapter);
    }


    @Override
    public void onDelete(long id) {
        GlucoseController.getInstance(this).delete(id);
        updateRows();
    }

    @Override
    public void onEdit(long id) {
        Intent intent = new Intent(this, GlucoseForm.class);
        intent.putExtra(getString(R.string.intent_key_glucose_id), id);
        startActivity(intent);
    }

    @Override
    public String[] getColumnsHeaders() {
        return new String[]{
                getString(R.string.glucose_measure),
                getString(R.string.date),
                getString(R.string.time)
        };
    }

    @Override
    public void updateRows() {
        updateModelsAccordingToLimit();
        listView.setAdapter(new GlucoseAdapter(this.models, DateTimeUtils.getInstance()));
    }

    @Override
    public int getTitleResourceId() {
        return R.string.glucose_history_title;
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
    public List getAllModels() {
        return GlucoseController.getInstance(this).listAll();
    }
}
