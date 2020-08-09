package com.project.symptoms.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.project.symptoms.R;
import com.project.symptoms.adapter.GlucoseLevelsAdapter;
import com.project.symptoms.db.controller.GlucoseLevelsController;
import com.project.symptoms.db.model.GlucoseLevels;

import java.util.List;

public class GlucoseLevelsInfoDialog extends Dialog{

    private GlucoseLevelsAdapter glucoseLevelsAdapter;

    public GlucoseLevelsInfoDialog(Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.glucose_levels);

        Button accept = findViewById(R.id.accept);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { dismiss(); }
        });

        listAll();

    }

    private void listAll() {
        List<GlucoseLevels> glucoseLevelsList = GlucoseLevelsController.getInstance(getContext()).listAll();
        glucoseLevelsAdapter = new GlucoseLevelsAdapter(getContext(), glucoseLevelsList);

        ListView listViewLevels = findViewById(R.id.glucose_levels_list_view);
        listViewLevels.setAdapter(glucoseLevelsAdapter);
    }
}
