package com.project.symptoms.db.dao;

import com.project.symptoms.db.model.Pressure;

import java.util.List;

public interface PressureDao {


    long insert(Pressure pressure) throws Exception;

    List<Pressure> listAll() throws Exception;

    boolean delete(long id) throws Exception;

    boolean update(long id, Pressure newValues) throws Exception;


}
