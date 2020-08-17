package com.project.symptoms.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.project.symptoms.R;
import com.project.symptoms.util.DateTimeUtils;

public class DateRangeForPDFDialog extends DialogFragment {

    private DateRangeDialogListener dateRangeDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View mainView = inflater.inflate(R.layout.pdf_dates_picker_dialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(mainView)
        .setTitle(R.string.date_range_dialog)
        // Add action buttons
        .setPositiveButton(R.string.accept, (dialog, id) ->
                dateRangeDialogListener.onDialogPositiveClick(DateRangeForPDFDialog.this))
        .setNegativeButton(R.string.cancel, (dialog, id) ->
                dateRangeDialogListener.onDialogNegativeClick(DateRangeForPDFDialog.this));

        TextView startDateView = mainView.findViewById(R.id.start_date_range);
        TextView endDateView = mainView.findViewById(R.id.end_date_range);
        DateTimeUtils.getInstance().registerAsDatePicker(startDateView);
        DateTimeUtils.getInstance().registerAsDatePicker(endDateView);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the DateRangeDialogListener so we can send events to the host
            dateRangeDialogListener = (DateRangeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement DateRangeDialogListener");
        }
    }

    public interface DateRangeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
}
