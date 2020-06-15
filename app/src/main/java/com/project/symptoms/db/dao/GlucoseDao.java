package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.Glucose;

public interface GlucoseDao {

    long insert(Glucose glucose) throws Exception;

    int update(Glucose glucose) throws Exception;

    int delete(int id) throws Exception;

    Glucose select(int id) throws Exception;

}
