package com.project.symptoms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.symptoms.R;
import com.project.symptoms.util.DateTimeUtils;

public class LoginActivity extends AppCompatActivity {

    EditText name, firstLastName, secondLastName, userId;
    TextView birthDate;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        name = findViewById(R.id.user_name);
        firstLastName = findViewById(R.id.user_first_last_name);
        secondLastName = findViewById(R.id.user_second_last_name);
        userId = findViewById(R.id.user_id);
        birthDate = findViewById(R.id.user_birth_date);
        continueButton = findViewById(R.id.finish_button);
        continueButton.setOnClickListener(v -> continueButtonPressed());
        DateTimeUtils.getInstance().registerAsDatePicker(birthDate);
    }

    private boolean fieldsEmpty(){
        return name.getText().toString().trim().length() == 0
                || firstLastName.getText().toString().trim().length() == 0
                || secondLastName.getText().toString().trim().length() == 0
                || userId.getText().toString().trim().length() == 0
                || birthDate.getText().toString().trim().length() == 0;
    }

    private void saveUserInfoInPref(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor =  prefs.edit();
        editor.putString(getResources().getString(R.string.sp_user_name), name.getText().toString());
        editor.putString(getResources().getString(R.string.sp_user_first_last_name), firstLastName.getText().toString());
        editor.putString(getResources().getString(R.string.sp_user_second_last_name), secondLastName.getText().toString());
        editor.putString(getResources().getString(R.string.sp_user_id), userId.getText().toString());
        editor.putString(getResources().getString(R.string.sp_user_birth_date), birthDate.getText().toString());
        editor.apply();
    }

    private void continueButtonPressed(){
        if (!fieldsEmpty()){
            saveUserInfoInPref();
            Bundle data = new Bundle();
            data.putBoolean(getResources().getString(R.string.bundle_key), true);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(data);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, getResources().getString(R.string.fields_not_filled) , Toast.LENGTH_SHORT).show();
        }
    }

}
