package com.project.symptoms;

import android.app.Activity;
import android.app.Presentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class BodySelection extends FragmentActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_selection);

        Button maleButton = findViewById(R.id.male_button);
        Button femaleButton = findViewById(R.id.female_button);

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor =  prefs.edit();
                editor.putString("body_type", "female");
                editor.commit();

                Intent intent = new Intent(BodySelection.this, MainActivity.class);
                startActivity(intent);
            }
        });

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor =  prefs.edit();
                editor.putString("body_type", "male");
                editor.commit();

                Intent intent = new Intent(BodySelection.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
