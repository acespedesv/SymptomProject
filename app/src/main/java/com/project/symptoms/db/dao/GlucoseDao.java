package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.GlucoseModel;

public interface GlucoseDao {

    long insert(GlucoseModel glucoseModel) throws Exception;

    int update(GlucoseModel glucoseModel) throws Exception;

    int delete(int id) throws Exception;

    GlucoseModel select(int id) throws Exception;

}
