package com.project.symptoms.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.symptoms.R;
import com.project.symptoms.db.model.GlucoseLevels;

import java.util.List;

public class GlucoseLevelsAdapter extends BaseAdapter {

    List<GlucoseLevels> glucoseLevelsList;
    GlucoseLevels glucoseLevel;
    Context context;

    public GlucoseLevelsAdapter(Context context, List<GlucoseLevels> glucoseLevelsList){
        this.glucoseLevelsList = glucoseLevelsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return glucoseLevelsList.size();
    }

    @Override
    public Object getItem(int position) {
        glucoseLevel = glucoseLevelsList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        glucoseLevel = glucoseLevelsList.get(position);
        return glucoseLevel.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.level, null);
        }
        glucoseLevel = glucoseLevelsList.get(position);

        TextView level = (TextView) view.findViewById(R.id.level);
        TextView fastingPlasma = (TextView) view.findViewById(R.id.fasting_plasma);
        TextView toleranceTest = (TextView) view.findViewById(R.id.tolerance_test);

        level.setText(glucoseLevel.getLevel());
        fastingPlasma.setText(glucoseLevel.getFastingPlasma());
        toleranceTest.setText(glucoseLevel.getToleranceTest());

        return view;
    }
}
