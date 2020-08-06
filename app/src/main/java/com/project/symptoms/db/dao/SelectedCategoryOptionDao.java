package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SelectedCategoryOptionModel;

import java.util.List;

public interface SelectedCategoryOptionDao {

    long insert(SelectedCategoryOptionModel selectedCategoryOptionModel) throws Exception;

    List<SelectedCategoryOptionModel> selectAllBySymptom(long symptomId) throws Exception;

    List<SelectedCategoryOptionModel> selectAll() throws Exception;

    int delete(long symptomId, long categoryId) throws Exception;

    int deleteAllBySymptom(long symptomId) throws Exception;

}
