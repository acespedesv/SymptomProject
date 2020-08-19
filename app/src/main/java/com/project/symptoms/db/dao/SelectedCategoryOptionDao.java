package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SelectedCategoryOptionModel;

import java.util.List;

public interface SelectedCategoryOptionDao {

    long insert(SelectedCategoryOptionModel selectedCategoryOptionModel) throws Exception;

    List<SelectedCategoryOptionModel> selectBySymptomId(long symptomId) throws Exception;

    List<SelectedCategoryOptionModel> selectAll() throws Exception;

    int delete(long symptomId, long categoryId) throws Exception;

    int deleteBySymptomId(long symptomId) throws Exception;

    int update(SelectedCategoryOptionModel newValues) throws Exception;
}
