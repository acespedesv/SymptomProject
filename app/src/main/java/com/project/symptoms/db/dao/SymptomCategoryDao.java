package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SymptomCategoryModel;

public interface SymptomCategoryDao {

    long insert(SymptomCategoryModel symptomModel) throws Exception;

    boolean delete(int id) throws Exception;

    boolean update(int id, SymptomCategoryModel newValues) throws Exception;
}
