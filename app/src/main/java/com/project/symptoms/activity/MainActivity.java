package com.project.symptoms.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.symptoms.db.controller.SelectedCategoryOptionController;
import com.project.symptoms.db.controller.SymptomController;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.dialog.CircleSizeSelectionDialog;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.R;
import com.project.symptoms.fragment.BodyFragment;
import com.project.symptoms.fragment.CalendarFragment;
import com.project.symptoms.util.DateTimeUtils;
import com.project.symptoms.view.BodyView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        CalendarFragment.OnFragmentInteractionListener,
        BodyFragment.OnFragmentInteractionListener,
        MainMenuFragment.OnFragmentInteractionListener,
        CircleSizeSelectionDialog.OnCircleSizeSelectedListener,
        CircleSizeSelectionDialog.OnCircleSizeUpdatedListener {

    private final long DEFAULT_SELECTED_SYMPTOM_ID_VALUE = -1;
    private BodyView bodyView;
    private Toolbar toolbar;
    private CircleSizeSelectionDialog sizeSelectionDialog;
    private BodyView.Circle currentCircle;
    private TextView dateTextView;
    private int currentBodySide;
    private long lastSymptomSelectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        try {
            updateSymptomsInBodyView();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // This is needed to be done again because of the context used in DateTimeUtils
        // changes when another view is also registcered as piker
        // TODO FIX THIS ISSUE in DateTimeUtils
        DateTimeUtils.getInstance().registerAsDatePicker(dateTextView);
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

        // Capture when text view for the date changes to be able to update the symptoms showed in the body view
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

        lastSymptomSelectedId = DEFAULT_SELECTED_SYMPTOM_ID_VALUE;
        registerForContextMenu(findViewById(R.id.body_fragment));

    }

    // Get all symptoms for current date from DB and add them to the BodyView
    private void updateSymptomsInBodyView() throws ParseException {
        currentBodySide = (bodyView.getState() == BodyView.State.BACK) ? 0 : 1;

        // Get current date from text view to filter the data in DB
        Date currentDate = DateTimeUtils.getInstance().getDateFromString(dateTextView.getText().toString());
        List<SymptomModel> symptomModels = SymptomController.getInstance(this).listAll(currentDate.getTime(), currentBodySide);

        // Instantiate new circles from DB data and replace them in the BodyView
        ArrayList<BodyView.Circle> circles = new ArrayList<>();
        for (SymptomModel symptomModel: symptomModels) {
            BodyView.Circle currentCircle = new BodyView.Circle(symptomModel.getCirclePosX(), symptomModel.getCirclePosY(), symptomModel.getCircleRadius());
            circles.add(currentCircle);
        }

        bodyView.replacePoints(circles);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        if (lastSymptomSelectedId != DEFAULT_SELECTED_SYMPTOM_ID_VALUE){
//            menu.setHeaderTitle(R.string.symptom_menu_title);
//            getMenuInflater().inflate(R.menu.symptom_menu, menu);
//        }
        menu.setHeaderTitle(R.string.symptom_menu_title);
        getMenuInflater().inflate(R.menu.symptom_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_symptom:
                updateSymptom(lastSymptomSelectedId);
            case R.id.finish_symptom:
                Toast.makeText(this, "Finalizar síntoma", Toast.LENGTH_LONG).show();
            case R.id.delete_symptom:
                try {
                    deleteSymptom(lastSymptomSelectedId);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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

    private SymptomModel getSymptomModelByCoordinates(float posX, float posY) throws ParseException {
        long today = DateTimeUtils.getInstance().getDateFromString(dateTextView.getText().toString()).getTime();
        List<SymptomModel> todaySymptoms = SymptomController.getInstance(this).listAll(today, 1);
        List<SymptomDistancePair> distances = new ArrayList<>();

        // Use pythagoras formula to calculate distances between points
        for (SymptomModel symptomModel : todaySymptoms) {
            float xDistance = Math.abs(posX - symptomModel.getCirclePosX());
            float yDistance = Math.abs(posY - symptomModel.getCirclePosY());
            double totalDistance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
            distances.add(new SymptomDistancePair(totalDistance, symptomModel.getSymptomId()));
        }

        // Using lambda expression to sort the list of pairs by distance
        Collections.sort(distances, (symptomDistancePair1, symptomDistancePair2) ->
                Double.compare(symptomDistancePair1.distance, symptomDistancePair2.distance));

        // Get the first id which is the nearest symptom id
        lastSymptomSelectedId = distances.get(0).symptomId;
        return SymptomController.getInstance(this).findById(lastSymptomSelectedId);

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
        data.putInt("State", currentBodySide);
        newIntent.putExtras(data);
        startActivity(newIntent);
    }

    private void updateSymptom(long symptomId){
        Intent newIntent = new Intent(this, SymptomForm.class);
        Bundle data = new Bundle();
        data.putLong("symptom_id", symptomId);
        newIntent.putExtras(data);
        startActivity(newIntent);

        // Reset the value
        lastSymptomSelectedId = DEFAULT_SELECTED_SYMPTOM_ID_VALUE;
    }

    private void deleteSymptom(final long symptomId) throws ParseException {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_sure_about_deleting)
                .setPositiveButton(R.string.alert_positive_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean symptomDeletionSuccess = SymptomController.getInstance(getApplicationContext()).deleteSymptomById(symptomId);
                        boolean categoriesDeletionSuccess = SelectedCategoryOptionController.getInstance(getApplicationContext()).deleteAllBySymptom(symptomId);
                        if (symptomDeletionSuccess && categoriesDeletionSuccess){
                            try { updateSymptomsInBodyView(); }
                            catch (ParseException e) { e.printStackTrace(); }
                        }
                    }
                })
                .setNegativeButton(R.string.alert_negative_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
        .show();

        // Reset the value
        lastSymptomSelectedId = DEFAULT_SELECTED_SYMPTOM_ID_VALUE;
    }

    // Class used to hold distances between symptoms coordinates
    private static class SymptomDistancePair{
        double distance;
        int symptomId;
        SymptomDistancePair(double distance, int symptomId){
            this.distance = distance;
            this.symptomId = symptomId;
        }
    }

}
