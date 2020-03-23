package com.project.symptoms;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity implements
        CalendarFragment.OnFragmentInteractionListener,
        BodyFragment.OnFragmentInteractionListener,
        MainMenuFragment.OnFragmentInteractionListener {

    BodyView bodyView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        bodyView = findViewById(R.id.bodyView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String body = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("body_type","None");
        Toast.makeText(this,"Welcome "+body, Toast.LENGTH_SHORT).show();

        if(body.equals("male")) bodyView.setBodyType(BodyView.BodyType.MALE);
        else if(body.equals("female")) bodyView.setBodyType(BodyView.BodyType.FEMALE);

        // Setup the flip button
        ImageButton flipButton = findViewById(R.id.flip_button);
        flipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bodyView.flip();
            }
        });

        // Set up bottom sheet
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.layout_bottom_sheet_seekbar,
                (LinearLayout)findViewById(R.id.bottomSheetContainer)
        );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
