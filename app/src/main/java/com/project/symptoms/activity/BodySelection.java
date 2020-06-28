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

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.project.symptoms.R;

public class BodySelection extends FragmentActivity{

    int selectedColor = Color.parseColor("#8DBF41");
    int normalColor = Color.parseColor("#d6d7d7");

    TextView currentSelectionText;

    // Rows
    final int FEMALE = 0;
    final int MALE = 1;

    // Columns
    final int KEYWORD = 0;
    final int NAME = 1;


    int selectedBodyType = MALE; // Be MALE OF FEMALE

    String bodyTypes[][] = {
            {"female","Mujer"},
            {"male","Hombre"}
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_selection);


        currentSelectionText = findViewById(R.id.current_selection);
        currentSelectionText.setText("Ninguno");


        final ImageButton maleButton = findViewById(R.id.male_button);
        final ImageButton femaleButton = findViewById(R.id.female_button);

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBodyType = FEMALE;
                updateSelectionText();

                // Swap color
                v.setBackgroundColor(selectedColor);
                maleButton.setBackgroundColor(normalColor);
            }
        });

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBodyType = MALE;
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
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor =  prefs.edit();
                editor.putString("body_type", bodyTypes[selectedBodyType][KEYWORD]);
                editor.apply();

                Intent intent = new Intent(BodySelection.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Reflect the current selection on the screen
     */
    private void updateSelectionText(){
        currentSelectionText.setText(bodyTypes[selectedBodyType][NAME]);
    }
}