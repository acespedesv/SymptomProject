package com.project.symptoms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.project.symptoms.activity.HistoryBase;
import com.project.symptoms.db.model.GlucoseModel;
import com.project.symptoms.db.model.PressureModel;
import com.project.symptoms.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureAdapter extends BaseAdapter {

    List<PressureModel> models;
    DateTimeUtils dateTimeUtils;

    public BloodPressureAdapter(List<PressureModel> models, DateTimeUtils dateTimeUtils) {
        super();
        this.models = models;
        this.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public long getItemId(int position) {
        return models.get(position).getId();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO optimize reuse

        PressureModel pressureModel = models.get(position);
        String readableDate = dateTimeUtils.DATE_PARSER.format(pressureModel.getDate());
        String readableTime = dateTimeUtils.TIME_FORMATTER.format(pressureModel.getTime());
        String values[] = new String[]{ ""+pressureModel.getSystolic(), ""+pressureModel.getDiastolic(), readableDate, readableTime};
        return HistoryBase.buildLinearLayout(parent, values, 10);
    }



}
