package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.SymptomModel;

import java.util.List;

public interface SymptomDao {

    long insert(SymptomModel symptomModel) throws Exception;

    List<SymptomModel> listAll() throws Exception;

    List<SymptomModel> listAll(long dateTime, int circleSide) throws Exception;

    boolean delete(int id) throws Exception;

    boolean update(int id, SymptomModel newValues) throws Exception;
}
