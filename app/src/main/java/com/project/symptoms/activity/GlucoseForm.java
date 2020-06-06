package com.project.symptoms.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.symptoms.Controller.GlucoseController;
import com.project.symptoms.R;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.util.DateTimeUtils;

public class GlucoseForm extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    Toolbar toolbar;

    EditText measureText;
    TextView dateView;
    TextView timeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_form);

        dateView = findViewById(R.id.date_input);
        timeView = findViewById(R.id.time_input);

        DateTimeUtils.getInstance().registerAsDatePicker(dateView);
        DateTimeUtils.getInstance().registerAsTimePicker(timeView);
    }

    public void onClick(View view) {
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        measureText = findViewById(R.id.glucose_measure);
        saveData();
    }

    private void saveData() {
        int glucoseValue = Integer.parseInt(measureText.getText().toString());
        String date = dateView.getText().toString();
        String hour = timeView.getText().toString();

        long id = GlucoseController.getInstance(this).insert(glucoseValue, date, hour);
        Toast.makeText(getApplicationContext(), "ID" + id, Toast.LENGTH_SHORT).show();

        /*Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);*/
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO: handle the event
    }
}
