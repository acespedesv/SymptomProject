package com.project.symptoms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.project.symptoms.R;
import com.project.symptoms.view.BodyView;

public class BodySelection extends FragmentActivity{

    int selectedColor = Color.parseColor("#8DBF41");
    int normalColor = Color.parseColor("#d6d7d7");

    SharedPreferences prefs;

    TextView currentSelectionText;

    String maleBody, femaleBody, noneSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_selection);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        maleBody = getString(R.string.body_type_male);
        femaleBody = getString(R.string.body_type_female);
        noneSelected = getString(R.string.preference_selected_body_type_default);

        currentSelectionText = findViewById(R.id.current_selection);
        currentSelectionText.setText(getCurrentBodySelection());


        final ImageButton maleButton = findViewById(R.id.male_button);
        final ImageButton femaleButton = findViewById(R.id.female_button);

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBodySelection(femaleBody);
                updateSelectionText();

                // Swap color
                 v.setBackgroundColor(selectedColor);
                 maleButton.setBackgroundColor(normalColor);
            }
        });

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBodySelection(maleBody);
                updateSelectionText();

                // Swap color
                v.setBackgroundColor(selectedColor);
                femaleButton.setBackgroundColor(normalColor);
            }
        });

        Button continueButton = findViewById(R.id.continue_button);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCurrentBodySelection().equals(noneSelected))
                    notifyChoiceNeeded();
                else {
                    startMainActivity();
                }
            }
        });

    }

    private void notifyChoiceNeeded() {
        Toast.makeText(this, getString(R.string.choice_required_to_continue), Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity() {
        Toast.makeText(this, getCurrentBodySelection(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }


    /**
     * Reflect the current selection on the screen
     */
    private void updateSelectionText(){
        currentSelectionText.setText(getCurrentBodySelection());
    }

    private String getCurrentBodySelection(){
        return prefs.getString(getString(R.string.preference_selected_body_type_key),
                getString(R.string.preference_selected_body_type_default));
    }

    private void saveBodySelection(String newValue){
        prefs.edit()
                .putString(getString(R.string.preference_selected_body_type_key), newValue)
                .commit();
    }
}
