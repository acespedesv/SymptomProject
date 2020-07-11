package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SelectedCategoryOptionModel;

import java.util.List;

public interface SelectedCategoryOptionDao {

    long insert(SelectedCategoryOptionModel selectedCategoryOptionModel) throws Exception;

    List<SelectedCategoryOptionModel> listAllBySymptom(int symptomId) throws Exception;

    List<SelectedCategoryOptionModel> listAll() throws Exception;

    boolean delete(int symptomId, int categoryId) throws Exception;

    boolean update(int id, SelectedCategoryOptionModel newValues) throws Exception;

}
