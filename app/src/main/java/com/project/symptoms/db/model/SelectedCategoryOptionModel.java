package com.project.symptoms.db.model;

public class SelectedCategoryOptionModel {

    private long symptomId, categoryOptionId;

    public SelectedCategoryOptionModel(long symptomId, long categoryOptionId) {
        this.symptomId = symptomId;
        this.categoryOptionId = categoryOptionId;
    }

    public long getSymptomId() { return symptomId; }

    public void setSymptomId(int symptomId) { this.symptomId = symptomId; }

    public long getCategoryOptionId() { return categoryOptionId; }

    public void setCategoryOptionId(int categoryOptionId) { this.categoryOptionId = categoryOptionId; }
}
