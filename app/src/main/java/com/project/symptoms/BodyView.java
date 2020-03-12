package com.project.symptoms;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class BodyView extends View implements AlertDialog.OnClickListener{

    // Used to draw the circles over the image
    private Paint redBrush;

    // Possible sizes to use
    private float CIRCLE_SMALL = 30f;
    private float CIRCLE_MEDIUM = 50f;
    private float CIRCLE_LARGE = 70f;

    // Options for the user to choose
    final String intensityOptions[] = {"Leve","Medio","Fuerte"};

    // The dialog to ask the intesity of the pain
    AlertDialog intensitySelectionDialog;

    // The actual size to use when drawing circles
    private float CIRCLE_RADIUS = CIRCLE_SMALL;

    // The image to draw on the screen
    private Drawable imageDrawable;

    // The (X,Y) pairs of the points to be drawn over the image
    private ArrayList<float[]> points;
    private float[] current_point = {0,0,0}; //X, Y, Radius



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

    public BodyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void addPoint(float x, float y, float radius){
        float[] point = {x, y, radius};
        points.add(point);
        invalidate();
    }

    private void init(){
        redBrush = new Paint();
        redBrush.setColor(Color.RED);
        points = new ArrayList<>();

        imageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img_male_front, null);

        // Default state
        setBodyType(BodyType.MALE);
        setState(State.FRONT);

        // Setup the intensity selection dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Seleccione la intensidad del dolor")
                .setItems(intensityOptions, this);
        intensitySelectionDialog = builder.create();

    }


    /**
     * Update {@link #imageDrawable} image to match the {@link #currentBodyType} and {@link #currentState}
     */

    private void updateImage(){
        int targetImageResId = R.drawable.img_male_front;// Default value

        if(currentBodyType == BodyType.FEMALE){
            if(currentState == State.FRONT){
                targetImageResId = R.drawable.img_female_front;
            }
            else{
                targetImageResId = R.drawable.img_female_back;
            }
        }
        else if(currentBodyType == BodyType.MALE){
            if(currentState == State.FRONT){
                targetImageResId = R.drawable.img_male_front;
            }
            else{
                targetImageResId = R.drawable.img_male_back;
            }
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

        // Set the dimensions to draw the image
        imageDrawable.setBounds(0,0,getWidth(),(int)(getHeight()*0.75f));
        imageDrawable.draw(canvas);

        // Draw the points over the image
        for(float[] point : points){
            canvas.drawCircle(point[0], point[1], point[2], redBrush);
        }


    }


    /**
     * When the image is touched
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            current_point[0] = event.getX();
            current_point[1] = event.getY();

            intensitySelectionDialog.show();


//                float x = event.getX(), y = event.getY();
//                Pair point = new Pair<Float, Float>(x,y);
//                points.add(point);
//
//                // Force redraw
//                invalidate();
        }
        return true;
    }

    // When intensity chosen from dialog
    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){
            case 0:
                current_point[2] = CIRCLE_SMALL;
                break;
            case 1:
                current_point[2] = CIRCLE_MEDIUM;
                break;
            case 2:
                current_point[2] = CIRCLE_LARGE;
                break;
        }

        addPoint(current_point[0], current_point[1], current_point[2]);
    }
}
