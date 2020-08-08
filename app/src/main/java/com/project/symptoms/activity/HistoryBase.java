package com.project.symptoms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
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
import com.project.symptoms.db.model.PressureModel;
import com.project.symptoms.fragment.MainMenuFragment;

import java.util.List;

public abstract class HistoryBase extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    ListView listView;
    int pageSize = 120;
    int maxRowsToShow = pageSize;
    Button loadMoreButton;
    List models;


    protected void hideLoadMoreButton(){
        listView.removeFooterView(loadMoreButton);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_base);


        setTitle(getTitleResourceId());

        listView = findViewById(R.id.data_list_view);

        setupListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRows();
    }

    public void setupListView(){
        View parentView = findViewById(R.id.rootLayout);
        LinearLayout headings = buildLinearLayout(parentView,
                getColumnsHeaders(),
                20);
        headings.setBackgroundColor(getColor(R.color.history_table_header_background));
        listView.addHeaderView(headings, null, false);

        loadMoreButton = new Button(this);
        loadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadMoreClicked();
            }
        });
        loadMoreButton.setText(R.string.load_more);
        listView.addFooterView(loadMoreButton);


        registerForContextMenu(listView);
    }


    public static LinearLayout buildLinearLayout(View parent, String values[], int verticalPadding){
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout row = (LinearLayout) layoutInflater.inflate(R.layout.history_row, null);
        for (String value: values) {
            TextView textView = new TextView(parent.getContext());
            textView.setText(value);
            textView.setTextColor(parent.getContext().getColor(R.color.colorMainWhite));
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

    public void updateModelsAccordingToLimit(){
        List all = getAllModels();
        if(all.size() == 0) {
            this.models = all;
            hideLoadMoreButton();
        }
        else{
            int limit = Math.min(all.size(), maxRowsToShow);
            this.models = all.subList(0, limit);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    // Methods to override in subclasses

    public abstract void onDelete(long id);

    public abstract void onEdit(long id);

    public abstract String[] getColumnsHeaders();

    public abstract void updateRows();

    public abstract void onLoadMoreClicked();

    public abstract int getTitleResourceId();

    public abstract List getAllModels();

}
