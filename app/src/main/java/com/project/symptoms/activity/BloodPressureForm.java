package com.project.symptoms.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.symptoms.db.controller.PressureController;
import com.project.symptoms.db.controller.BloodPressureLevelsController;
import com.project.symptoms.db.model.BloodPressureLevels;
import com.project.symptoms.util.DateTimeUtils;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.R;

import java.util.List;

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

        checkValues(systolicValue, diastolicValue);

    }

    private void checkValues(int systolicValue, int diastolicValue) {
        List<BloodPressureLevels> bloodPressureLevelsList = BloodPressureLevelsController.getInstance(BloodPressureForm.this).listAll();
        BloodPressureLevels normal = bloodPressureLevelsList.get(0);
        BloodPressureLevels elevated = bloodPressureLevelsList.get(1);
        BloodPressureLevels hypertensionStage1 = bloodPressureLevelsList.get(2);
        BloodPressureLevels hypertensionStage2 = bloodPressureLevelsList.get(3);
        BloodPressureLevels hypotension = bloodPressureLevelsList.get(4);

        String message = "Los valores ingresados indican que presenta una presion arterial ";
        String recommendation = "\n\n Se le recomienda consultar a su medico.";

        //If values are different to normal category
        if (!(systolicValue <= normal.getSystolicMaximum() &&
                systolicValue >= normal.getSystolicMinimum() &&
                diastolicValue <= normal.getDiastolicMaximum() &&
                diastolicValue >= normal.getDiastolicMinimum())){

            AlertDialog.Builder pressureAlert  = new AlertDialog.Builder(BloodPressureForm.this);

            if (systolicValue <= elevated.getSystolicMaximum() &&
                    systolicValue >= elevated.getSystolicMinimum() &&
                    diastolicValue <= elevated.getDiastolicMaximum() &&
                    diastolicValue >= elevated.getDiastolicMinimum()){

                pressureAlert.setMessage(message + elevated.getCategory() + recommendation);

            }
            else if ((systolicValue <= hypertensionStage1.getSystolicMaximum() &&
                    systolicValue >= hypertensionStage1.getSystolicMinimum()) ||
                    (diastolicValue <= hypertensionStage1.getDiastolicMaximum() &&
                            diastolicValue >= hypertensionStage1.getDiastolicMinimum())){

                pressureAlert.setMessage(message + hypertensionStage1.getCategory() + recommendation);
            }
            else if ((systolicValue <= hypertensionStage2.getSystolicMaximum() &&
                    systolicValue >= hypertensionStage2.getSystolicMinimum()) ||
                    (diastolicValue <= hypertensionStage2.getDiastolicMaximum() &&
                            diastolicValue >= hypertensionStage2.getDiastolicMinimum())){

                pressureAlert.setMessage(message + hypertensionStage2.getCategory() + recommendation);
            }

            else if ((systolicValue <= hypotension.getSystolicMaximum() &&
                    systolicValue >= hypotension.getSystolicMinimum()) ||
                    (diastolicValue <= hypotension.getDiastolicMaximum() &&
                            diastolicValue >= hypotension.getDiastolicMinimum())){
                pressureAlert.setMessage(message + hypotension.getCategory() + recommendation);
            }

             pressureAlert.setCancelable(false)
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = pressureAlert.create();
            alertDialog.setTitle("ALERTA");
            alertDialog.show();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
