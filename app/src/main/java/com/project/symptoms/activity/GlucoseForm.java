package com.project.symptoms.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.project.symptoms.Controller.GlucoseController;
import com.project.symptoms.DAO.GlucoseDao;
import com.project.symptoms.Model.Glucose;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.R;
import com.project.symptoms.util.DateTimeUtils;

public class GlucoseForm extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    Toolbar toolbar;

    Button saveButton;
    EditText measureText;
    TextView dateView;
    TextView timeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_form);

        dateView = findViewById(R.id.date_input);
        timeView = findViewById(R.id.time_input);
        measureText = findViewById(R.id.glucose_measure);

        DateTimeUtils.getInstance().registerAsDatePicker(dateView);
        DateTimeUtils.getInstance().registerAsTimePicker(timeView);
        init();

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {

        GlucoseController glucoseController = new GlucoseController(measureText, dateView, timeView, GlucoseForm.this);
        int id = glucoseController.saveData();
        Toast.makeText(getApplicationContext(), "ID" + id, Toast.LENGTH_SHORT).show();

        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO: handle the event
    }


}
