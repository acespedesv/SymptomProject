package com.project.symptoms.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.project.symptoms.R;
import com.project.symptoms.activity.SymptomForm;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class BodyView extends View {

    // Used to draw the circles over the image
    private Paint redBrush;

    // Max/Min seekBar value that represents circle sizes range
    private int MAX_SEEKBAR_VALUE = 100;
    private int MIN_SEEKBAR_VALUE = 10;

    // The image to draw on the screen
    private Drawable imageDrawable;

    // The (X,Y) pairs of the points to be drawn over the image
    private ArrayList<float[]> points;
    // Temporary point that will be drawn several times
    // whenever user is choosing the size of a new circle
    private float[] tmpPoint = new float[3];

    // Used to know the current state in order to change it accordingly
    private State currentState;
    private BodyType currentBodyType;

    // Enums to limit the options to use
    public enum BodyType{MALE, FEMALE};
    public enum State{FRONT, BACK};

    public BodyView(Context context) {
        super(context);
        init();
    }

    public BodyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BodyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void addPoint(float x, float y, float radius){
        float[] point = {x, y, radius};
        points.add(point);
        invalidate();
    }

    private void addTmpPoint(float x, float y, float radius){
        tmpPoint = new float[3];
        tmpPoint[0] = x;
        tmpPoint[1] = y;
        tmpPoint[2] = radius;
        invalidate();
    }

    private void init(){
        redBrush = new Paint();
        redBrush.setAntiAlias(false);
        redBrush.setStyle(Paint.Style.FILL);
        redBrush.setColor(Color.RED);

        points = new ArrayList<>();

        imageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img_male_front, null);

        // Default state
        setBodyType(BodyType.MALE);
        setState(State.FRONT);
    }

    /**
     * Update {@link #imageDrawable} image to match the {@link #currentBodyType} and {@link #currentState}
     */

    private void updateImage(){
        int targetImageResId = R.drawable.img_male_front;// Default value

        if(currentBodyType == BodyType.FEMALE){
            targetImageResId = (currentState == State.FRONT) ? R.drawable.img_female_front : R.drawable.img_female_back;
        }
        else if(currentBodyType == BodyType.MALE){
            targetImageResId = (currentState == State.FRONT) ? R.drawable.img_male_front : R.drawable.img_male_back;
        }

        imageDrawable = ResourcesCompat.getDrawable(getResources(), targetImageResId, null);
        invalidate(); // Force redrawing
    }


    /**
     * Update the {@link #currentBodyType} attribute
     */
    public void setBodyType(BodyType newBodyType){
        currentBodyType = newBodyType;
        updateImage();
    }

    /**
     * Update the {@link #currentState} attribute
     */
    public void setState(State newState){
        currentState = newState;
        updateImage();
    }

    /**
     * Toggle {@link #currentState} and empty {@link #points}
     */
    public void flip(){
        currentState = (currentState==State.FRONT) ? State.BACK : State.FRONT;
        points.clear();
        updateImage();
    }

    /**
     * Draw the current image and draw the list of points over the image
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBodyImage(canvas);

        drawPermanentPoints(canvas);

        drawTemporaryPoint(canvas);

    }

    private void drawPermanentPoints(Canvas canvas) {
        for(float[] point : points)
            canvas.drawCircle(point[0], point[1], point[2], redBrush);
    }

    private void drawTemporaryPoint(Canvas canvas) {
        if(tmpPoint != null)
            canvas.drawCircle(tmpPoint[0], tmpPoint[1], tmpPoint[2], redBrush);
    }

    private void drawBodyImage(Canvas canvas) {
        imageDrawable.setBounds(calculateCenteredBounds());
        imageDrawable.draw(canvas);
    }

    private Rect calculateCenteredBounds() {
        float PERCENTAGE_OF_HEIGHT_TO_USE = 0.8f; // 0 to 1 scale
        float PERCENTAGE_OF_WIDTH_TO_USE = 0.5f;

        int final_height = (int) (getHeight() * PERCENTAGE_OF_HEIGHT_TO_USE);

        float remainingFreeWidth = getWidth() * (1 - PERCENTAGE_OF_WIDTH_TO_USE);

        int sideMargin = (int) (remainingFreeWidth / 2);

        int left = sideMargin;
        int right = 3*sideMargin;
        int top = 0;
        int bottom = final_height;

        return new Rect(left,top,right,bottom);
    }


    /**
     * When the image is touched
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float xPos = event.getX(), yPos = event.getY();
            // Let user choose circle size
            chooseCircleSize(xPos, yPos);
        }
        return true;
    }

    private void chooseCircleSize(final float xPos, final float yPos) {

        // Set up bottom sheet dialog
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);

        // Inflate bottom sheet view to add it to the dialog
        View bottomSheetView = LayoutInflater.from(getContext()).inflate(
                R.layout.layout_bottom_sheet_seekbar,
                (LinearLayout)findViewById(R.id.bottomSheetContainer)
        );

        bottomSheetDialog.setContentView(bottomSheetView);

        // Set up seek bar
        final SeekBar seekBar = bottomSheetView.findViewById(R.id.seekBar);
        seekBar.setMax(MAX_SEEKBAR_VALUE);
        seekBar.setProgress(MIN_SEEKBAR_VALUE);

        // Add initial guide circle
        addTmpPoint(xPos, yPos, Integer.valueOf(MIN_SEEKBAR_VALUE).floatValue());

        // Listen to seek bar changes made by user
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                // Avoid circles of size 0
                if(i == 0) i += MIN_SEEKBAR_VALUE;

                // Clean and add new tmpPoint
                tmpPoint = null;
                addTmpPoint(xPos, yPos, Integer.valueOf(i).floatValue());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bottomSheetDialog.setCancelable(false); // Don't allow the user to press back
        bottomSheetDialog.show();

        Button doneButton = bottomSheetView.findViewById(R.id.size_done_button);
        doneButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: Save x and y pos from this circle in DB

                // Once the size was chosen draw that circle and clean the tmp one
                tmpPoint = null;
                addPoint(xPos, yPos, Integer.valueOf(seekBar.getProgress()).floatValue());
                Intent i = new Intent(getContext(), SymptomForm.class);
                // Recommended when starting a new activity out of Activity context
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(i);
                bottomSheetDialog.dismiss();

                startSymptomForm();
            }
        });

    }

    private void startSymptomForm() {
        getContext().startActivity(new Intent(getContext(), SymptomForm.class));
    }
}
