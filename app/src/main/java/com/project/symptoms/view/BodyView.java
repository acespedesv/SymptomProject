package com.project.symptoms.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
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
import com.project.symptoms.fragment.BodyFragment;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.net.URI;
import java.util.ArrayList;
import java.util.Currency;

public class BodyView extends View {

    // Used to draw the circles over the image
    private Paint redBrush;

    // The image to draw on the screen
    private Drawable imageDrawable;

    // The (X,Y) pairs of the points to be drawn over the image
    private ArrayList<Circle> points;

    // Temporary point that will be drawn several times
    // whenever user is choosing the size of a new circle
    private Circle temporaryPoint;

    // Used to know the current state in order to change it accordingly
    private State currentState;
    private BodyType currentBodyType;

    // Enums to limit the options to use
    public enum BodyType{MALE, FEMALE};
    public enum State{FRONT, BACK};


    Fragment parentFragment;

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

    public State getState(){ return currentState; }

    public void addPoint(Circle circle){
        Circle newPoint = new Circle(circle.x, circle.y, circle.radius);
        points.add(newPoint);
        invalidate();
    }

    public void replacePoints(ArrayList<Circle> circles){
        temporaryPoint = null;
        points.clear();
        for (Circle circle: circles) {
            points.add(new Circle(circle.x, circle.y, circle.radius));
        }
        circles.clear();
        invalidate();
    }

    public void setTemporaryPoint(Circle point){
        temporaryPoint = point;
        invalidate();
    }

    public ArrayList<Circle> getPoints(){ return points; }

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
        temporaryPoint = null;
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
        for(Circle point : points)
            canvas.drawCircle(point.x, point.y, point.radius, redBrush);
    }

    private void drawTemporaryPoint(Canvas canvas) {
        if(temporaryPoint != null)
            canvas.drawCircle(temporaryPoint.x, temporaryPoint.y, temporaryPoint.radius, redBrush);
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

            try {
                BodyFragment bodyFragment = (BodyFragment) parentFragment;

                String str = String.format("bodyview:/clicked?x=%f&y=%f",
                xPos, yPos);
                Uri uri = Uri.parse(str);
                bodyFragment.onButtonPressed(uri);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;

    }


    public void setParentFragment(Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    // Data-Only class
    // Implements Parcelable to be able to send an instance trough Bundle between intents
    public static class Circle implements Parcelable {
        public float x;
        public float y;
        public float radius;

        public Circle(float x, float y, float radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        protected Circle(Parcel in) {
            x = in.readFloat();
            y = in.readFloat();
            radius = in.readFloat();
        }

        public static final Creator<Circle> CREATOR = new Creator<Circle>() {
            @Override
            public Circle createFromParcel(Parcel in) {
                return new Circle(in);
            }

            @Override
            public Circle[] newArray(int size) {
                return new Circle[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeFloat(x);
            dest.writeFloat(y);
            dest.writeFloat(radius);
        }
    }
}
