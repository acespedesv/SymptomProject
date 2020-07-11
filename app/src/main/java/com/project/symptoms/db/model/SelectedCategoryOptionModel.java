package com.project.symptoms.db.model;

public class SelectedCategoryOptionModel {

    private int symptomId, categoryOptionId;

    public SelectedCategoryOptionModel(int symptomId, int categoryOptionId) {
        this.symptomId = symptomId;
        this.categoryOptionId = categoryOptionId;
    }

    public int getSymptomId() { return symptomId; }

    public void setSymptomId(int symptomId) { this.symptomId = symptomId; }

    public int getCategoryOptionId() { return categoryOptionId; }

    public void setCategoryOptionId(int categoryOptionId) { this.categoryOptionId = categoryOptionId; }
}
