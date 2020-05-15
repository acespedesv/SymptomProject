package com.project.symptoms.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.R;
import com.project.symptoms.util.DateTimeUtils;

public class GlucoseForm extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glucose_form);
        TextView dateView = findViewById(R.id.date_input);
        TextView timeView = findViewById(R.id.time_input);

        DateTimeUtils.getInstance().registerAsDatePicker(dateView);
        DateTimeUtils.getInstance().registerAsTimePicker(timeView);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO: handle the event
    }

}
