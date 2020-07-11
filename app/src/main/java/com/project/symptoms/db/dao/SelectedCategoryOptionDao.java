package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SelectedCategoryOptionModel;

import java.util.List;

public interface SelectedCategoryOptionDao {

    long insert(SelectedCategoryOptionModel selectedCategoryOptionModel) throws Exception;

    List<SelectedCategoryOptionModel> listAllBySymptom(int symptomId) throws Exception;

    List<SelectedCategoryOptionModel> listAll() throws Exception;

    boolean delete(long symptomId, long categoryId) throws Exception;

    boolean update(long id, SelectedCategoryOptionModel newValues) throws Exception;

}
