package com.project.symptoms.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.symptoms.db.controller.PressureController;
import com.project.symptoms.util.DateTimeUtils;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.R;

public class BloodPressureForm extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    protected Toolbar toolbar;
    protected Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_form);

        TextView hour = findViewById(R.id.hour_pressure);
        DateTimeUtils.getInstance().registerAsTimePicker(hour);

        TextView date = findViewById(R.id.date_pressure);
        DateTimeUtils.getInstance().registerAsDatePicker(date);
        init();

    }

    private void init(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData(){
        // Gather all the data fields
        EditText systolicView = findViewById(R.id.systolic);
        EditText diastolicView = findViewById(R.id.diastolic);
        TextView hourView =  findViewById(R.id.hour_pressure);
        TextView dateView = findViewById(R.id.date_pressure);

        int systolicValue = Integer.parseInt(systolicView.getText().toString());
        int diastolicValue = Integer.parseInt(diastolicView.getText().toString());
        String time = hourView.getText().toString();
        String date = dateView.getText().toString();

        long id = PressureController.getInstance(this).insert(systolicValue, diastolicValue, date, time);

        if(id != -1){
            String text = getResources().getString(R.string.value_successfully_saved);
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
