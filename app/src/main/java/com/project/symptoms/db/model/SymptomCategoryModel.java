package com.project.symptoms.db.model;

public class SymptomCategoryModel {

    private int categoryId;
    private String name;

    public SymptomCategoryModel(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public SymptomCategoryModel(String name) {
        this(-1, name);
    }

    public int getCategoryId() { return categoryId; }

    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
