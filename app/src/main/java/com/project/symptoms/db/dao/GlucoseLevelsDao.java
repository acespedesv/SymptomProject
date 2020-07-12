package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.GlucoseLevels;

import java.util.List;

public interface GlucoseLevelsDao {

    long insert(GlucoseLevels glucose) throws Exception;

    List<GlucoseLevels> listAll() throws Exception;
}
