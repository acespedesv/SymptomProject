package com.project.symptoms.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;

import com.project.symptoms.R;
import com.project.symptoms.fragment.SettingsFragment;
import com.project.symptoms.util.NotificationWrapper;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationWrapper.getInstance(this).startReminderFor(2);
    }
}
