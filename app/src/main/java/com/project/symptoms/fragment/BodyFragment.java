package com.project.symptoms.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.symptoms.R;
import com.project.symptoms.view.BodyView;

import java.text.ParseException;

public class BodyFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public BodyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_body, container, false);
        // Enable the BodyView to communicate to it's parent
        BodyView bodyView = layout.findViewById(R.id.bodyView);
        bodyView.setParentFragment(this);
        return layout;
    }

    // When the body view is pressed
    public void onViewPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
