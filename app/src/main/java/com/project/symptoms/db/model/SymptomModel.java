package com.project.symptoms.db.model;

public class SymptomModel {
    private int symptomId;
    private float circlePosX, circlePosY;
    private long startDate, startTime;
    private String description, intensity, causingDrug, causingFood;
    private int intermittence;
    private float circleRadius;
    private int circleSide;
    private int duration;

    public SymptomModel(int symptomId, float circlePosX, float circlePosY, long startDate, long startTime,
                        int duration, String description, String intensity,
                        String causingDrug, String causingFood, int intermittence, float circleRadius, int circleSide) {
        this.symptomId = symptomId;
        this.circlePosX = circlePosX;
        this.circlePosY = circlePosY;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
        this.description = description;
        this.intensity = intensity;
        this.causingDrug = causingDrug;
        this.causingFood = causingFood;
        this.intermittence = intermittence;
        this.circleRadius = circleRadius;
        this.circleSide = circleSide;
    }

    public SymptomModel(float circlePosX, float circlePosY, long startDate, long startTime, int duration,
                        String description, String intensity, String causingDrug, String causingFood,
                        int intermittence, float circleRadius, int circleSide) {
        this.circlePosX = circlePosX;
        this.circlePosY = circlePosY;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
        this.description = description;
        this.intensity = intensity;
        this.causingDrug = causingDrug;
        this.causingFood = causingFood;
        this.intermittence = intermittence;
        this.circleRadius = circleRadius;
        this.circleSide = circleSide;
    }

    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    public float getCirclePosX() {
        return circlePosX;
    }

    public void setCirclePosX(float circlePosX) {
        this.circlePosX = circlePosX;
    }

    public float getCirclePosY() {
        return circlePosY;
    }

    public void setCirclePosY(float circlePosY) {
        this.circlePosY = circlePosY;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getDuration() { return duration; }

    public void setDuration(int duration) { this.duration = duration; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getCausingDrug() {
        return causingDrug;
    }

    public void setCausingDrug(String causingDrug) {
        this.causingDrug = causingDrug;
    }

    public String getCausingFood() {
        return causingFood;
    }

    public void setCausingFood(String causingFood) {
        this.causingFood = causingFood;
    }

    public int getIntermittence() {
        return intermittence;
    }

    public void setIntermittence(int intermittence) {
        this.intermittence = intermittence;
    }

    public float getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(float circleRadius) {
        this.circleRadius = circleRadius;
    }

    public int getCircleSide() {
        return circleSide;
    }

    public void setCircleSide(int circleSide) {
        this.circleSide = circleSide;
    }
}
