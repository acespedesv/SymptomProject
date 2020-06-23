package com.project.symptoms.activity;

import android.app.Dialog;
import android.content.Context;
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
import com.project.symptoms.db.controller.GlucoseLevelsController;
import com.project.symptoms.db.model.Glucose;
import com.project.symptoms.db.model.GlucoseLevels;
import com.project.symptoms.dialog.GlucoseLevelsInfoDialog;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.util.DateTimeUtils;

import java.util.List;

public class GlucoseForm extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

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

        DateTimeUtils.getInstance().registerAsDatePicker(dateView);
        DateTimeUtils.getInstance().registerAsTimePicker(timeView);
        init();

        /*Glucose glucose = GlucoseController.getInstance(this).select(4);
        measureText = findViewById(R.id.glucose_measure);
        measureText.setText(Integer.toString(glucose.getValue()));*/

    }

    private void init(){
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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

        long id = GlucoseController.getInstance(this).insert(glucoseValue, date, hour);
        Toast.makeText(getApplicationContext(), "ID" + id, Toast.LENGTH_SHORT).show();

        /*Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);*/
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
