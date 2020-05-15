package com.project.symptoms.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.symptoms.R;
import com.project.symptoms.activity.BloodPressureForm;
import com.project.symptoms.activity.GlucoseForm;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMenuFragment} factory method to
 * create an instance of this fragment.
 */
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
                openHistoryActivity();
            }
        });
    }

    private void openHistoryActivity() {
        //TODO: Implement this
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
