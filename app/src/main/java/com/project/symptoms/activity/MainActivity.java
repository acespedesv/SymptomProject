package com.project.symptoms.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.symptoms.db.controller.SymptomController;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.dialog.CircleSizeSelectionDialog;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.R;
import com.project.symptoms.fragment.BodyFragment;
import com.project.symptoms.fragment.CalendarFragment;
import com.project.symptoms.util.DateTimeUtils;
import com.project.symptoms.view.BodyView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        CalendarFragment.OnFragmentInteractionListener,
        BodyFragment.OnFragmentInteractionListener,
        MainMenuFragment.OnFragmentInteractionListener,
        CircleSizeSelectionDialog.OnCircleSizeSelectedListener,
        CircleSizeSelectionDialog.OnCircleSizeUpdatedListener {

    private BodyView bodyView;
    private Toolbar toolbar;
    private CircleSizeSelectionDialog sizeSelectionDialog;
    private BodyView.Circle currentCircle;
    private TextView dateTextView;
    private int circleSide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create the database
        DBHelper database = new DBHelper(this);
        init();
        try {
            updateSymptomsInBodyView();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void launchBodySelection(){
        startActivity(new Intent(this, BodySelection.class));
    }

    private void init() {
        bodyView = findViewById(R.id.bodyView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String body = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("body_type", "None");

        if (body.equals("None")) {
            launchBodySelection();
        }

        // Make the view match the selected body type
        if(body.equals("male")) bodyView.setBodyType(BodyView.BodyType.MALE);
        else if(body.equals("female")) bodyView.setBodyType(BodyView.BodyType.FEMALE);

        // Setup the flip button
        ImageView flipButton = findViewById(R.id.flip_button);
        flipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bodyView.flip();
                try {
                    updateSymptomsInBodyView();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        dateTextView = findViewById(R.id.current_date);
        DateTimeUtils.getInstance().registerAsDatePicker(dateTextView);

        // Capture when text view to be able to update the symptoms showed in the body view
        dateTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    updateSymptomsInBodyView();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // Get all symptoms for current date from DB and add them to the BodyView
    private void updateSymptomsInBodyView() throws ParseException {
        circleSide = (bodyView.getState() == BodyView.State.BACK) ? 0 : 1;


        // Get current date from text view to filter the data in DB
        Date currentDate = DateTimeUtils.getInstance().getDateFromString(dateTextView.getText().toString());
        List<SymptomModel> symptomModels = SymptomController.getInstance(this).listAll(currentDate.getTime(), circleSide);

        // Instantiate new circles from DB data and replace them in the BodyView
        ArrayList<BodyView.Circle> circles = new ArrayList<>();
        for (SymptomModel symptomModel: symptomModels) {
            BodyView.Circle currentCircle = new BodyView.Circle(symptomModel.getCirclePosX(), symptomModel.getCirclePosY(), symptomModel.getCircleRadius());
            circles.add(currentCircle);
        }

        bodyView.replacePoints(circles);
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
        switch (id){
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
        startActivity(new Intent(this, About.class));
    }

    private void startSettingsActivity() {
        startActivity(new Intent(this, Settings.class));
    }

    public void launchCircleSizeSelectionDialog(){
        if(sizeSelectionDialog == null){
            sizeSelectionDialog = new CircleSizeSelectionDialog(this, R.style.BottomSheetDialogTheme);
            sizeSelectionDialog.setOnCircleSelectedListener(this);
            sizeSelectionDialog.setOnCircleSizeUpdateListener(this);
        }
        sizeSelectionDialog.show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        float x = Float.parseFloat(uri.getQueryParameter("x").replace(",","."));
        float y = Float.parseFloat(uri.getQueryParameter("y").replace(",","."));
        if( currentCircle == null)
            currentCircle = new BodyView.Circle(0,0,10);
        currentCircle.x = x;
        currentCircle.y = y;
        bodyView.setTemporaryPoint(currentCircle);
        launchCircleSizeSelectionDialog();
    }

    @Override
    public void OnCircleSizeUpdate(float radius) {
        currentCircle.radius = radius;
        bodyView.setTemporaryPoint(currentCircle);
    }

    @Override
    public void OnCircleSizeSelected(float radius) {
        sizeSelectionDialog.dismiss();
        currentCircle.radius = radius;
        bodyView.addPoint(currentCircle);
        launchSymptomForm();
    }

    // Create new intent and send current circles information through Bundle
    private void launchSymptomForm() {
        Intent newIntent = new Intent(this, SymptomForm.class);
        Bundle data = new Bundle();
        data.putParcelable("Circle", currentCircle);
        data.putString("Date", dateTextView.getText().toString());
        data.putString("Time", DateTimeUtils.getInstance().getCurrentTime());
        data.putInt("State", circleSide);
        newIntent.putExtras(data);
        startActivity(newIntent);
    }
}
