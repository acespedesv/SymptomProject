package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.BloodPressureLevels;

import java.util.List;

public interface BloodPressureLevelsDao {

    long insert(BloodPressureLevels pressureLevel) throws Exception;

    List<BloodPressureLevels> selectAll() throws Exception;
}
