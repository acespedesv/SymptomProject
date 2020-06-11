package com.project.symptoms.db.model;

public class SymptomModel {
    private int symptomId;
    private float circlePosX, circlePosY;
    private long creationDateTime;
    private float circleRadius;

    public SymptomModel(int symptomId, int circlePosX, int circlePosY, long creationDateTime, float circleRadius) {
        this.symptomId = symptomId;
        this.circlePosX = circlePosX;
        this.circlePosY = circlePosY;
        this.creationDateTime = creationDateTime;
        this.circleRadius = circleRadius;
    }

    public SymptomModel(float circlePosX, float circlePosY, long creationDateTime, float circleRadius) {
        this.circlePosX = circlePosX;
        this.circlePosY = circlePosY;
        this.creationDateTime = creationDateTime;
        this.circleRadius = circleRadius;
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

    public long getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(long creationDateTime) {
        this.creationDateTime = creationDateTime;
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
}
