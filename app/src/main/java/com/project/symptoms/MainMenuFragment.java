package com.project.symptoms;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMenuFragment} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment {

    private View glucoseButton, bloodPressureButton, historyButton;
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
        // Get all menu options
        bloodPressureButton = rootFragmentView.findViewById(R.id.blood_pressure_icon);
        glucoseButton = rootFragmentView.findViewById(R.id.glucose_icon);
        historyButton = rootFragmentView.findViewById(R.id.calendar_icon);

        // Set event listeners
        bloodPressureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBloodPressureFormActivity();
            }
        });
        glucoseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGlucoseFormActivity();
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryActivity();
            }
        });
    }

    private void openHistoryActivity() {
        //TODO: Implement this
    }

    private void openGlucoseFormActivity() {
        Intent bloodPressureActivity = new Intent(getContext(), GlucoseForm.class);
        startActivity(bloodPressureActivity);
    }

    private void openBloodPressureFormActivity(){
        Intent bloodPressureActivity = new Intent(getContext(), BloodPressureForm.class);
        startActivity(bloodPressureActivity);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
