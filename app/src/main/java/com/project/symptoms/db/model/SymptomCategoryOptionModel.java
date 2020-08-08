package com.project.symptoms.db.model;

public class SymptomCategoryOptionModel {

    private long categoryOptionId, categoryFkId;
    private int iconResourceId;
    private String categoryOptionName;

    public SymptomCategoryOptionModel(long categoryOptionId, long categoryFkId, String categoryOptionName, int iconResourceId) {
        this.categoryOptionId = categoryOptionId;
        this.categoryFkId = categoryFkId;
        this.categoryOptionName = categoryOptionName;
        this.iconResourceId = iconResourceId;
    }

    public SymptomCategoryOptionModel(int categoryFkId, String categoryOptionName, int iconResourceId) {
        this(-1, categoryFkId, categoryOptionName, iconResourceId);
    }

    public long getCategoryOptionId() { return categoryOptionId; }

    public void setCategoryOptionId(int categoryOptionId) { this.categoryOptionId = categoryOptionId; }

    public long getCategoryFkId() { return categoryFkId; }

    public void setCategoryFkId(int categoryFkId) { this.categoryFkId = categoryFkId; }

    public String getCategoryOptionName() { return categoryOptionName; }

    public void setCategoryOptionName(String categoryOptionName) { this.categoryOptionName = categoryOptionName; }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }
}
