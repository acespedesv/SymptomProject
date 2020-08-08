package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.PressureModel;

import java.util.List;

public interface PressureDao {

    long insert(PressureModel pressureModel) throws Exception;

    List<PressureModel> selectAll() throws Exception;

    PressureModel select(long id) throws Exception;

    int delete(long id) throws Exception;

    int update(PressureModel newValues) throws Exception;


}
