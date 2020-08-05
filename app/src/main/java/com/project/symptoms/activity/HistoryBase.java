package com.project.symptoms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.project.symptoms.R;
import com.project.symptoms.adapter.GlucoseAdapter;
import com.project.symptoms.db.controller.GlucoseController;
import com.project.symptoms.db.model.GlucoseModel;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.util.DateTimeUtils;

import java.util.ArrayList;

public abstract class HistoryBase extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_history);

        setTitle(getTitleResourceId());

        listView = findViewById(R.id.data_list_view);

        setupListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchModels();
    }

    public void setupListView(){
        View parentView = findViewById(R.id.rootLayout);
        LinearLayout headings = buildLinearLayout(parentView,
                getColumnsHeaders(),
                20);
        listView.addHeaderView(headings, null, false);

        fetchModels();

        Button button = new Button(this);
        button.setText(R.string.load_more);
        listView.addFooterView(button);

        registerForContextMenu(listView);
    }

    public static LinearLayout buildLinearLayout(View parent, String values[], int verticalPadding){
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout row = (LinearLayout) layoutInflater.inflate(R.layout.history_row, null);
        for (String value: values) {
            TextView textView = new TextView(parent.getContext());
            textView.setText(value);
            textView.setPadding(0, verticalPadding, 0, verticalPadding);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            textView.setLayoutParams(params);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            row.addView(textView);
        }
        return row;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.glucose_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        long modelId = info.id;
        switch (item.getItemId()) {
            case R.id.delete:
                onDelete(modelId);
                break;
            case R.id.edit:
                onEdit(modelId);
                break;
        }
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // Override
    public abstract void onDelete(long id);
//    public abstract void onDelete(long id){
//        GlucoseController.getInstance(this).delete(id);
//        fetchModels();
//    }

    // Override
    public abstract void onEdit(long id);
//    public void onEdit(long id){
//        startActivity(new Intent(this, GlucoseForm.class));
//    }

    // Override
    public abstract String[] getColumnsHeaders();
//    private String[] getColumnsHeaders() {
//        return new String[]{
//                getString(R.string.glucose_measure),
//                getString(R.string.column_heading_date),
//                getString(R.string.column_heading_time)
//        };
//    }

    // Override
    public abstract void fetchModels();
//    public void fetchModels(){
//        ArrayList models = GlucoseController.getInstance(this).listAll();
//        listView.setAdapter(new GlucoseAdapter(models, DateTimeUtils.getInstance()));
//    }

    public abstract int getTitleResourceId();
}
