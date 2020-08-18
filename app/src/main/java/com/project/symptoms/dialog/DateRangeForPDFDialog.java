package com.project.symptoms.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
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

        TextView dialogTitle = new TextView(getContext());
        dialogTitle.setHeight(125);
        dialogTitle.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
        dialogTitle.setText(R.string.date_range_dialog);
        dialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        dialogTitle.setTextColor(Color.WHITE);
        dialogTitle.setGravity(Gravity.CENTER);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(mainView)
        .setCustomTitle(dialogTitle)
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
