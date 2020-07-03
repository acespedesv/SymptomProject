package com.project.symptoms.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.symptoms.R;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;

public class SymptomOptionView extends RelativeLayout  implements View.OnClickListener{

    private boolean isChecked;

    private ImageView backgroundCircle;
    private ImageView borderCircle;
    private ImageView icon;
    private TextView label;


    public SymptomOptionView(Context context) {
        super(context);
        initializeAttributes();
    }

    public SymptomOptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes();
    }

    public SymptomOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeAttributes();
    }

    public SymptomOptionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeAttributes();
    }

    private void initializeAttributes(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.symptom_option,this);

        isChecked = false;
        this.label = findViewWithTag("label");
        this.backgroundCircle = findViewWithTag("background_circle");
        this.borderCircle = findViewWithTag("border_circle");
        this.icon = findViewWithTag("icon");
        setOnClickListener(this);

    }

    public void toggle(){
        int newBorderColorId = (isChecked) ? R.color.symptom_option_normal_border : R.color.symptom_option_selected_border;
        int newBackgroundColorId = (isChecked) ? R.color.symptom_option_normal_background : R.color.symptom_option_selected_background;

        backgroundCircle.setBackgroundTintList(ColorStateList.valueOf( getResources().getColor(newBackgroundColorId, null)));
        borderCircle.setBackgroundTintList(ColorStateList.valueOf( getResources().getColor(newBorderColorId, null)));

        isChecked = !isChecked;
    }

    public void setChecked(boolean isChecked){
        if(isChecked != this.isChecked){
            toggle();
        }
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void setSymptomOption(SymptomCategoryOptionModel model){
        this.icon.setImageResource(getContext().getResources().getIdentifier("ic_acne","drawable",getContext().getPackageName())); //TODO FIX THIS
        this.label.setText(model.getCategoryOptionName());
    }

    public String getName(){
        return this.label.getText().toString();
    }

    @Override
    public void onClick(View v) {
        toggle();
    }
}
