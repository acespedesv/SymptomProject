package com.project.symptoms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GlucoseForm extends AppCompatActivity implements
        MainMenuFragment.OnFragmentInteractionListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private final boolean FORMAT_12H = false;

    private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE dd MMM yyyy");

    private final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(" hh:mm a");

    private final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy/MM/dd");

    TextView dateView;
    TextView timeView;

    /**
     * Set the text to current time by default and setup the picker dialog
     */
    private void initTimePicker(){
        // Init the dialog
        int current_hour = Calendar.getInstance().get(Calendar.HOUR);
        int current_minute = Calendar.getInstance().get(Calendar.MINUTE);
        final TimePickerDialog hourPickerDialog = new TimePickerDialog(this,this, current_hour, current_minute, FORMAT_12H);

        // Set the trigger to open the dialog
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hourPickerDialog.show();
            }
        });

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
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,current_year, current_month, current_day);

        // Set the trigger to open the dialog
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // Show the current date by default
        updateDateView(current_year, current_month, current_day);
    }

    private void updateDateView(int year, int month, int day){

        String text = "";
        try{
            month++; // BECAUSE MONTHS START COUNTING AT ZERO
            text =  DATE_FORMATTER.format(DATE_PARSER.parse(year+"/"+month+"/"+day));

        }catch (Exception e){
            e.printStackTrace();
        };
        dateView.setText(text);
    }

    private void updateTimeView(int hour, int minute){
        String text =  TIME_FORMATTER.format(new Date(0,0,0,hour,minute));
        timeView.setText(text);
    }

    private void findViews(){
        dateView = findViewById(R.id.date_input);
        timeView = findViewById(R.id.time_input);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glucose_form);

        findViews();

        initDatePicker();

        initTimePicker();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        updateDateView(year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        updateTimeView(hourOfDay, minute);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO: handle the event
    }

}
