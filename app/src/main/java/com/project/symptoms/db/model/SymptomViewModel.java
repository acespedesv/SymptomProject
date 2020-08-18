package com.project.symptoms.db.model;

public class SymptomViewModel {

    private long symptomId;
    private long categoryOptionId;
    private String categoryOptionName;
    private long categoryId;
    private String categoryName;

    public SymptomViewModel(long symptomId, long categoryOptionId, String categoryOptionName, long categoryId, String categoryName) {
        this.symptomId = symptomId;
        this.categoryOptionId = categoryOptionId;
        this.categoryOptionName = categoryOptionName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public long getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(long symptomId) {
        this.symptomId = symptomId;
    }

    public long getCategoryOptionId() {
        return categoryOptionId;
    }

    public void setCategoryOptionId(long categoryOptionId) {
        this.categoryOptionId = categoryOptionId;
    }

    public String getCategoryOptionName() {
        return categoryOptionName;
    }

    public void setCategoryOptionName(String categoryOptionName) {
        this.categoryOptionName = categoryOptionName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
