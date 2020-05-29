package com.project.symptoms.Controller;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.symptoms.DAO.GlucoseDao;
import com.project.symptoms.Model.Glucose;
import com.project.symptoms.activity.GlucoseForm;

public class GlucoseController {

    private EditText measure;
    private TextView dateView;
    private TextView timeView;
    private Context context;
    private Glucose glucose;
    private GlucoseDao glucoseDao;

    public GlucoseController(EditText measure, TextView date, TextView time, Context context) {
        this.measure = measure;
        this.dateView = date;
        this.timeView = time;
        this.context = context;
    }

    public int saveData() {
        String value = measure.getText().toString();
        String date = "" + dateView.getText();
        String time = "" + timeView.getText();
        //Creates the model
        glucose = new Glucose(Integer.parseInt(value), date, time);
        //Calls the dao
        glucoseDao = new GlucoseDao(context);
        return glucoseDao.insert(glucose);
    }

    public int deleteData() {
        int id = 2;
        glucose = new Glucose(id);
        //Calls the dao
        glucoseDao = new GlucoseDao(context);
        return glucoseDao.delete(glucose.getId());
    }
}
