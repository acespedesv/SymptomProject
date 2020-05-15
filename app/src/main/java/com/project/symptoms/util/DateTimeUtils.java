package com.project.symptoms.util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private  final boolean FORMAT_12H = false;

    private  final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE dd MMM yyyy");

    private  final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(" hh:mm a");

    private  final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy/MM/dd");

    private  TextView dateView;
    private  TextView timeView;

    private  Context context;

    private  TimePickerDialog timePickerDialog;
    private  DatePickerDialog datePickerDialog;

    private static DateTimeUtils instance;

    private DateTimeUtils(){

    }


    public static DateTimeUtils getInstance(){
        if( instance == null)
            instance = new DateTimeUtils();
        return instance;
    }

    /**
     * Set the text to current time by default and setup the picker dialog
     */
    private void initTimePicker(){
        // Init the dialog
        int current_hour = Calendar.getInstance().get(Calendar.HOUR);
        int current_minute = Calendar.getInstance().get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(context,this, current_hour, current_minute, FORMAT_12H);

        updateTimeView(current_hour, current_minute);
    }

    /**
     * Set the text to current date by default and setup the picker dialog
     */
    private void initDatePicker(){
        // Init the dialog
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        int current_month = Calendar.getInstance().get(Calendar.MONTH);
        int current_day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(context, this, current_year, current_month, current_day);

        updateDateView(current_year, current_month, current_day);

    }

    public void registerAsTimePicker(TextView v){
        timeView = v;

        setContext(v.getContext());

        initTimePicker();

        // Set the trigger to open the dialog
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }

    public void registerAsDatePicker(TextView v){
        dateView = v;

        setContext(v.getContext());

        initDatePicker();

        // Set the trigger to open the dialog
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }


    private void setContext(Context v){
        context = v;
    }

    private void updateDateView(int year, int month, int day){

        String text = "";
        try{
            month++; // BECAUSE MONTHS START COUNTING AT ZERO
            text =  DATE_FORMATTER.format(DATE_PARSER.parse(year+"/"+month+"/"+day));

        }catch (Exception e){
            e.printStackTrace();
        }
        dateView.setText(text);
    }

    private void updateTimeView(int hour, int minute){
        String text =  TIME_FORMATTER.format(new Date(0,0,0,hour,minute));
        timeView.setText(text);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        updateDateView(year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        updateTimeView(hourOfDay, minute);
    }
}
