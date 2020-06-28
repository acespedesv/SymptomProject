package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SymptomCategoryOptionModel;

public interface SymptomCategoryOptionDao {

    long insert(SymptomCategoryOptionModel symptomModel) throws Exception;

    boolean delete(int id) throws Exception;

    boolean update(int id, SymptomCategoryOptionModel newValues) throws Exception;

}
