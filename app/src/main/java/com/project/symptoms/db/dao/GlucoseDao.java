package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.GlucoseModel;

import java.util.ArrayList;
import java.util.List;

public interface GlucoseDao {

    long insert(GlucoseModel glucoseModel) throws Exception;

    int update(GlucoseModel glucoseModel) throws Exception;

    int delete(long id) throws Exception;

    GlucoseModel select(long id) throws Exception;

    List<GlucoseModel> selectAll() throws Exception;

}
