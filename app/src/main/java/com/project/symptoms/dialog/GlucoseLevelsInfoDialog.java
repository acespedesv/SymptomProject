package com.project.symptoms.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.project.symptoms.R;
import com.project.symptoms.adapter.GlucoseLevelsAdapter;
import com.project.symptoms.db.controller.GlucoseLevelsController;
import com.project.symptoms.db.model.GlucoseLevels;

import org.w3c.dom.Text;

import java.util.List;

public class GlucoseLevelsInfoDialog {

    Context context;
    GlucoseLevelsAdapter glucoseLevelsAdapter;

    public GlucoseLevelsInfoDialog(Context context){
        this.context = context;

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.glucose_levels);
        Button accept = (Button) dialog.findViewById(R.id.accept);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        List<GlucoseLevels> glucoseLevelsList = GlucoseLevelsController.getInstance(context).listAll();
        glucoseLevelsAdapter = new GlucoseLevelsAdapter(context, glucoseLevelsList);

        ListView listViewLevels = dialog.findViewById(R.id.glucose_levels_list_view);
        listViewLevels.setAdapter(glucoseLevelsAdapter);

    }

    private void listAll(){

        List<GlucoseLevels> glucoseLevelsList = GlucoseLevelsController.getInstance(context).listAll();
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.glucose_levels, null);

//
//        View layoutLevel = layoutInflater.inflate(R.layout.level, null);
//        level = layoutLevel.findViewById(R.id.level);
//        fastingPlasma = layoutLevel.findViewById(R.id.fasting_plasma);
//        toleranceTest = layoutLevel.findViewById(R.id.tolerance_test);
//        ArrayAdapter<GlucoseLevels> glucoseLevelsArrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, glucoseLevelsList);

//        for(GlucoseLevels glucoseLevel : glucoseLevelsList){
//
//            level.setText(glucoseLevel.getLevel());
//            fastingPlasma.setText(glucoseLevel.getFastingPlasma());
//            toleranceTest.setText(glucoseLevel.getToleranceTest());
//
//            listViewLevels.addView(level);
//            listViewLevels.addView(fastingPlasma);
//            listViewLevels.addView(toleranceTest);
//        }
        glucoseLevelsAdapter = new GlucoseLevelsAdapter(context, glucoseLevelsList);
        ListView listViewLevels = layout.findViewById(R.id.glucose_levels_list_view);
        listViewLevels.setAdapter(glucoseLevelsAdapter);










    }
}
