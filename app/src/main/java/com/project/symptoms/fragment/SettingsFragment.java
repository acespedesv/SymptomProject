package com.project.symptoms.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.project.symptoms.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}