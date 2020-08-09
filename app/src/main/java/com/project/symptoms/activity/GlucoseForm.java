package com.project.symptoms.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.symptoms.db.controller.GlucoseController;
import com.project.symptoms.R;
import com.project.symptoms.db.model.GlucoseModel;
import com.project.symptoms.dialog.GlucoseLevelsInfoDialog;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.util.DateTimeUtils;


public class GlucoseForm extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {



    private long glucoseId; // When called for edit
    private final long NO_ID = -1;
    private final long FAILURE = -1;

    Toolbar toolbar;
    EditText measureText;
    TextView dateView;
    TextView timeView;
    protected Button saveButton;

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

        glucoseId = getIntent().getLongExtra(getString(R.string.intent_key_glucose_id), NO_ID);
        if(glucoseId != NO_ID){
            populateForEdit(glucoseId);
        }

    }

    private void populateForEdit(long glucoseId) {
        GlucoseModel model = GlucoseController.getInstance(this).select(glucoseId);
        measureText.setText(""+model.getValue());
        dateView.setText(DateTimeUtils.getInstance().DATE_FORMATTER.format(model.getDate()));
        timeView.setText(DateTimeUtils.getInstance().TIME_FORMATTER.format(model.getTime()));
    }

    private void init(){
        toolbar = findViewById(R.id.toolbar);

        saveButton = findViewById(R.id.save_button);
        ImageButton glucoseLevels = findViewById(R.id.glucose_levels_button);

        glucoseLevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new GlucoseLevelsInfoDialog(GlucoseForm.this);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        measureText = findViewById(R.id.glucose_measure);
        dateView = findViewById(R.id.date_input);
        timeView = findViewById(R.id.time_input);

        int glucoseValue = Integer.parseInt(measureText.getText().toString());
        String date = dateView.getText().toString();
        String hour = timeView.getText().toString();

        String messageToShow = "";
        long result = FAILURE;
        if(glucoseId == NO_ID) {
            result = GlucoseController.getInstance(this).insert(glucoseValue, date, hour);
            messageToShow = getString(R.string.value_successfully_saved);
        }
        else{
            result = GlucoseController.getInstance(this).update(glucoseId, glucoseValue, date, hour);
            messageToShow = getString(R.string.value_successfully_updated);
        }
        if(result != FAILURE){
            Toast.makeText(this, messageToShow, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData() {
        int glucoseValue = Integer.parseInt(measureText.getText().toString());
        String date = dateView.getText().toString();
        String hour = timeView.getText().toString();

        int id = GlucoseController.getInstance(this).update(1, glucoseValue, date, hour);
        Toast.makeText(getApplicationContext(), "ID" + id, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO: handle the event
    }
}
