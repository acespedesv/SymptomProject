package com.project.symptoms.db.model;

public class SymptomModel {
    private int symptomId;
    private float circlePosX, circlePosY;
    private long creationDate, creationTime;
    private float circleRadius;

    public SymptomModel(int symptomId, float circlePosX, float circlePosY, long creationDate, long creationTime, float circleRadius) {
        this.symptomId = symptomId;
        this.circlePosX = circlePosX;
        this.circlePosY = circlePosY;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.circleRadius = circleRadius;
    }

    public SymptomModel(float circlePosX, float circlePosY, long creationDate, long creationTime, float circleRadius) {
        this.circlePosX = circlePosX;
        this.circlePosY = circlePosY;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
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

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) { this.creationDate = creationDate; }

    public long getCreationTime() { return creationTime; }

    public void setCreationTime(long creationTime) { this.creationTime = creationTime; }

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
