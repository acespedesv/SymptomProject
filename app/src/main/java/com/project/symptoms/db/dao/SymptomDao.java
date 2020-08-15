package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.db.model.SymptomViewModel;

import java.util.List;

public interface SymptomDao {

    long insert(SymptomModel symptomModel) throws Exception;

    List<SymptomModel> listAll() throws Exception;

    List<SymptomModel> listAll(long dateTime, int circleSide) throws Exception;

    List<SymptomModel> select(long initialDate, long finalDate) throws Exception;

    List<SymptomViewModel> selectFromView(long symptomId) throws Exception;

    boolean delete(long id) throws Exception;

    boolean update(long id, SymptomModel newValues) throws Exception;

    SymptomModel getById(long id) throws Exception;
}
