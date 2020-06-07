package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.Glucose;

public interface GlucoseDao {

    long insert(Glucose glucose) throws Exception;
    boolean update(Glucose glucose) throws Exception;
    int delete(int id) throws Exception;
    boolean select(int id) throws Exception;

}
