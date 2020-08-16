package com.project.symptoms.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.project.symptoms.R;
import com.project.symptoms.activity.About;
import com.project.symptoms.activity.Settings;

public class ActionBarFragment extends Fragment {
    Context includingContext;
    Toolbar toolbar;


    public ActionBarFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        toolbar = (Toolbar) inflater.inflate(R.layout.fragment_action_bar, container, false);
        AppCompatActivity activity = ((AppCompatActivity)includingContext);
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.home_icon);
        setHasOptionsMenu(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return toolbar;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        includingContext = context;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                startSettingsActivity();
                break;
            case R.id.action_about:
                startAboutActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startAboutActivity() {
        startActivity(new Intent(getContext(), About.class));
    }

    private void startSettingsActivity() {
        startActivity(new Intent(getContext(), Settings.class));
    }
}
