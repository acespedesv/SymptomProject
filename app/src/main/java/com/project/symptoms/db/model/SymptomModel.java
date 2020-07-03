package com.project.symptoms.db.model;

public class SymptomModel {
    private int symptomId;
    private float circlePosX, circlePosY;
    private long startTime, endTime;
    private float circleRadius;
    private int circleSide;

    // All attributes
    public SymptomModel(int symptomId, float circlePosX, float circlePosY, long startTime, long endTime, float circleRadius, int circleSide) {
        this.symptomId = symptomId;
        this.circlePosX = circlePosX;
        this.circlePosY = circlePosY;
        this.startTime = startTime;
        this.endTime = endTime;
        this.circleRadius = circleRadius;
        this.circleSide = circleSide;
    }

    // All attributes except id
    public SymptomModel(float circlePosX, float circlePosY, long startTime, long endTime, float circleRadius, int circleSide) {
        this(-1, circlePosX, circlePosY, startTime, endTime, circleRadius, circleSide);
    }

    public float getCirclePosX() {
        return circlePosX;
    }

    public void setCirclePosX(int circlePosX) {
        this.circlePosX = circlePosX;
    }

    public float getCirclePosY() {
        return circlePosY;
    }

    public void setCirclePosY(int circlePosY) {
        this.circlePosY = circlePosY;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public float getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(float circleRadius) {
        this.circleRadius = circleRadius;
    }

    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    public int getCircleSide() { return circleSide; }

    public void setCircleSide(int circleSide) { this.circleSide = circleSide; }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
