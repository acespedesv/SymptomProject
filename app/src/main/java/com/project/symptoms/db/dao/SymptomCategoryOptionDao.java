package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SymptomCategoryOptionModel;

import java.util.List;

public interface SymptomCategoryOptionDao {

    long insert(SymptomCategoryOptionModel symptomModel) throws Exception;

    SymptomCategoryOptionModel selectSymptomCategoryOption(String name) throws Exception;

    List<SymptomCategoryOptionModel> selectAll() throws Exception;

    SymptomCategoryOptionModel select(long categoryOptionId) throws Exception;

}
