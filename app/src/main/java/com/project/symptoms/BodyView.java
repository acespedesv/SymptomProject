package com.project.symptoms;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class BodyView extends View {

    // Used to draw the circles over the image
    private Paint redBrush;
    private float CIRCLE_RADIUS = 40f;

    // The image to draw on the screen
    private Drawable imageDrawable;

    // The (X,Y) pairs of the points to be drawn over the image
    private ArrayList<Pair<Float, Float>> points;



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

    private void init(){
        redBrush = new Paint();
        redBrush.setColor(Color.RED);
        points = new ArrayList<Pair<Float,Float>>();

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
     * Toggle {@link #currentState}
     */
    public void flip(){
        currentState = (currentState==State.FRONT) ? State.BACK : State.FRONT;
        updateImage();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // Set the dimensions to draw the image
        imageDrawable.setBounds(0,0,getWidth(),(int)(getHeight()*0.75f));
        imageDrawable.draw(canvas);

        // Draw the points over the image
        for(Pair point : points){
            canvas.drawCircle((float)point.first, (float)point.second, CIRCLE_RADIUS, redBrush);
        }


    }


    /**
     * When the image is touched
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x = event.getX(), y = event.getY();
                Pair point = new Pair<Float, Float>(x,y);
                points.add(point);

                // Force redraw
                invalidate();

                break;


        }
        return true;
    }
}
