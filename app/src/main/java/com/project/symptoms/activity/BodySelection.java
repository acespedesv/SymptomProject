package com.project.symptoms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.project.symptoms.R;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.controller.GlucoseLevelsController;
import com.project.symptoms.db.controller.BloodPressureLevelsController;
import com.project.symptoms.db.controller.SymptomCategoryController;
import com.project.symptoms.db.controller.SymptomCategoryOptionController;

public class BodySelection extends FragmentActivity{

    String bodyPreferenceKey;

    SymptomCategoryController symptomCategoryController;
    SymptomCategoryOptionController symptomCategoryOptionController;

    private String noneSelected = "None";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.body_selection);

        bodyPreferenceKey = getString(R.string.preference_selected_body_type_key);

        setButtonsListeners();


    }

    private void setButtonsListeners() {
        final ImageButton maleButton = findViewById(R.id.male_button);

        final ImageButton femaleButton = findViewById(R.id.female_button);

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.body_selection_background1, getTheme()));
                maleButton.setBackground(getResources().getDrawable(R.drawable.gradient_background, getTheme()));
                saveBodyTypeToPreferences(getString(R.string.preference_selected_body_type_female));
            }
        });

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.body_selection_background1, getTheme()));
                femaleButton.setBackground(getResources().getDrawable(R.drawable.gradient_background, getTheme()));
                saveBodyTypeToPreferences(getString(R.string.preference_selected_body_type_male));
            }
        });

        Button continueButton = findViewById(R.id.continue_button);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onContinueButtonClicked();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(selectionAlreadyMade())
            launchMainActivity();
        else
            initialDBInsertion();
        }

    private boolean selectionAlreadyMade(){
        return ! getBodyTypeInPreferences().equals(noneSelected);
    }

    /**  Perform initial DB insertion of:
             - symptom categories (MUST be inserted before category options)
             - symptom category options
             - glucose reference values
             - pressure reference values
     */
    private void initialDBInsertion(){
        symptomCategoryController = SymptomCategoryController.getInstance(this);
        symptomCategoryController.insert();

        symptomCategoryOptionController = SymptomCategoryOptionController.getInstance(this);
        symptomCategoryOptionController.insert();

        GlucoseLevelsController.getInstance(this).insert();
        BloodPressureLevelsController.getInstance(this).insert();
    }

    private void onContinueButtonClicked(){
        if(selectionAlreadyMade())
            launchMainActivity();
        else{
            notifySelectionNeeded();
        }
    }

    private void notifySelectionNeeded() {
        Toast.makeText(this, getString(R.string.selection_is_needed), Toast.LENGTH_SHORT)
                .show();
    }

    private void launchMainActivity() {
        Intent intent = new Intent(BodySelection.this, MainActivity.class);
        startActivity(intent);
    }

    private void saveBodyTypeToPreferences(String value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor =  prefs.edit();
        editor.putString(bodyPreferenceKey, value);
        editor.apply();
    }

    private String getBodyTypeInPreferences(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String valueSaved = prefs.getString(bodyPreferenceKey, noneSelected);
        return valueSaved;
    }
}

