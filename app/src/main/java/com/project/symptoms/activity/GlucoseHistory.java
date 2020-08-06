package com.project.symptoms.activity;

import android.content.Intent;

import com.project.symptoms.R;
import com.project.symptoms.adapter.GlucoseAdapter;
import com.project.symptoms.db.controller.GlucoseController;
import com.project.symptoms.util.DateTimeUtils;

import java.util.List;

public class GlucoseHistory extends HistoryBase {
    @Override
    public void onDelete(long id) {
        GlucoseController.getInstance(this).delete(id);
        fetchModels();
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
    public void fetchModels() {
        List models = GlucoseController.getInstance(this).listAll();
        listView.setAdapter(new GlucoseAdapter(models, DateTimeUtils.getInstance()));
    }

    @Override
    public int getTitleResourceId() {
        return R.string.glucose_history_title;
    }
}
