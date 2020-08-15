package com.project.symptoms.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.symptoms.R;
import com.project.symptoms.activity.BloodPressureForm;
import com.project.symptoms.activity.GlucoseForm;
import com.project.symptoms.activity.GlucoseHistory;

public class MainMenuFragment extends Fragment {

    private TextView glucoseTextView, bloodPressureTextView;
    private ImageView historyButton;
    private View rootFragmentView;
    private OnFragmentInteractionListener mListener;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initFragmentUI() {
        // Get all menu option views
        bloodPressureTextView = rootFragmentView.findViewById(R.id.blood_pressure_text);
        glucoseTextView = rootFragmentView.findViewById(R.id.glucose_text);
        historyButton = rootFragmentView.findViewById(R.id.calendar_icon);

        // Set event listeners
        bloodPressureTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBloodPressureFormActivity();
            }
        });
        glucoseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGlucoseFormActivity();
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryMenu();
            }
        });
    }

    private void openHistoryMenu() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.history_selection_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT);

        dialog.findViewById(R.id.glucose_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GlucoseHistory.class));
            }
        });
        dialog.findViewById(R.id.pressure_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GlucoseHistory.class));
            }
        });
        dialog.findViewById(R.id.cancel_hisotry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    private void openGlucoseFormActivity() {
        Intent glucoseFormActivity = new Intent(getContext(), GlucoseForm.class);
        startActivity(glucoseFormActivity);
    }

    private void openBloodPressureFormActivity(){
        Intent bloodPressureFormActivity = new Intent(getContext(), BloodPressureForm.class);
        startActivity(bloodPressureFormActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootFragmentView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        initFragmentUI();
        return rootFragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Needed for the fragment to communicate with the activity that includes it
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
