package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SymptomCategoryOptionModel;

import java.util.List;

public interface SymptomCategoryOptionDao {

    long insert(SymptomCategoryOptionModel symptomModel) throws Exception;

    SymptomCategoryOptionModel selectSymptomCategoryOption(String name) throws Exception;

    List<SymptomCategoryOptionModel> listAll() throws Exception;

    SymptomCategoryOptionModel listById(long categoryOptionId) throws Exception;

    boolean delete(int id) throws Exception;

    boolean update(int id, SymptomCategoryOptionModel newValues) throws Exception;

}
