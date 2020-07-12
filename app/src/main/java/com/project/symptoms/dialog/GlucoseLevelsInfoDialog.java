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

public class GlucoseLevelsInfoDialog {

    private Context context;
    private GlucoseLevelsAdapter glucoseLevelsAdapter;
    final Dialog dialog;

    public GlucoseLevelsInfoDialog(Context context){
        this.context = context;

        dialog = new Dialog(context);
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
        listAll();

        dialog.show();
    }

    private void listAll() {
        List<GlucoseLevels> glucoseLevelsList = GlucoseLevelsController.getInstance(context).listAll();
        glucoseLevelsAdapter = new GlucoseLevelsAdapter(context, glucoseLevelsList);

        ListView listViewLevels = dialog.findViewById(R.id.glucose_levels_list_view);
        listViewLevels.setAdapter(glucoseLevelsAdapter);
    }
}
