package com.project.symptoms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.project.symptoms.R;
import com.project.symptoms.activity.HistoryBase;
import com.project.symptoms.db.model.GlucoseModel;
import com.project.symptoms.util.DateTimeUtils;
import java.util.ArrayList;
import java.util.List;

public class GlucoseAdapter extends BaseAdapter {

    List<GlucoseModel> models;
    DateTimeUtils dateTimeUtils;

    public GlucoseAdapter(List<GlucoseModel> models, DateTimeUtils dateTimeUtils) {
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
        GlucoseModel glucoseModel = models.get(position);
        String readableDate = dateTimeUtils.DATE_PARSER.format(glucoseModel.getDate());
        String readableTime = dateTimeUtils.TIME_FORMATTER.format(glucoseModel.getTime());
        String values[] = new String[]{ ""+glucoseModel.getValue(), readableDate, readableTime};
        int verticalPadding = 10;
        LinearLayout row = HistoryBase.buildLinearLayout(parent, values, verticalPadding);
        int colorId = (position % 2 == 0) ? R.color.history_table_even_background : R.color.history_table_odd_background;
        row.setBackgroundColor(parent.getContext().getColor(colorId));
        return row;
    }

}
