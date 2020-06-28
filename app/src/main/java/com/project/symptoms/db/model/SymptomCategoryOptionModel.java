package com.project.symptoms.db.model;

public class SymptomCategoryOptionModel {

    private int categoryOptionId, categoryFkId;
    private String categoryOptionName;

    public SymptomCategoryOptionModel(int categoryOptionId, int categoryFkId, String categoryOptionName) {
        this.categoryOptionId = categoryOptionId;
        this.categoryFkId = categoryFkId;
        this.categoryOptionName = categoryOptionName;
    }

    public SymptomCategoryOptionModel(int categoryFkId, String categoryOptionName) {
        this.categoryFkId = categoryFkId;
        this.categoryOptionName = categoryOptionName;
    }

    public int getCategoryOptionId() { return categoryOptionId; }

    public void setCategoryOptionId(int categoryOptionId) { this.categoryOptionId = categoryOptionId; }

    public int getCategoryFkId() { return categoryFkId; }

    public void setCategoryFkId(int categoryFkId) { this.categoryFkId = categoryFkId; }

    public String getCategoryOptionName() { return categoryOptionName; }

    public void setCategoryOptionName(String categoryOptionName) { this.categoryOptionName = categoryOptionName; }
}
