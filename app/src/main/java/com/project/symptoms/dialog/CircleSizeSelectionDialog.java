package com.project.symptoms.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.project.symptoms.R;

public class CircleSizeSelectionDialog extends BottomSheetDialog {

    // Max/Min seekBar value that represents circle sizes range
    private int MAX_SEEKBAR_VALUE = 100;
    private int MIN_SEEKBAR_VALUE = 10;

    private OnCircleSizeSelectedListener sizeSelectedLister;
    private OnCircleSizeUpdatedListener sizeUpdateLister;

    private SeekBar seekBar;

    public CircleSizeSelectionDialog(@NonNull Context context) {
        super(context);
        initialize();
    }

    public CircleSizeSelectionDialog(@NonNull Context context, int theme) {
        super(context, theme);
        initialize();
    }

    public CircleSizeSelectionDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initialize();
    }

    private void initialize(){
        setContentView( R.layout.layout_bottom_sheet_seekbar);

        initializeSeekBar();

        initializeDoneButton();

        setCancelable(false); // Don't allow the user to press back
    }

    private void initializeDoneButton() {
        Button doneButton = findViewById(R.id.size_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float currentSize = Integer.valueOf(seekBar.getProgress()).floatValue();
                sizeSelectedLister.OnCircleSizeSelected(currentSize);
            }
        });
    }

    private void initializeSeekBar() {
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(MAX_SEEKBAR_VALUE);
        seekBar.setProgress(MIN_SEEKBAR_VALUE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                // Avoid circles of size 0
                if(i == 0) i += MIN_SEEKBAR_VALUE;

                float currentSize = Integer.valueOf(i).floatValue();

                sizeUpdateLister.OnCircleSizeUpdate(currentSize);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setOnCircleSizeUpdateListener(OnCircleSizeUpdatedListener sizeUpdateListener){
        this.sizeUpdateLister = sizeUpdateListener;
    }

    public void setOnCircleSelectedListener(OnCircleSizeSelectedListener sizeSelectedListener){
        this.sizeSelectedLister = sizeSelectedListener;
    }


    public interface OnCircleSizeUpdatedListener {
        public void OnCircleSizeUpdate(float radius);
    }

    public interface OnCircleSizeSelectedListener{
        public void OnCircleSizeSelected(float radius);
    }


    // Always start with the minimum size
    @Override
    public void show(){
        seekBar.setProgress(MIN_SEEKBAR_VALUE);
        super.show();

    }
}
