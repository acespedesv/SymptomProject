package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SymptomModel;

import java.util.List;

public class SymptomDaoImpl implements SymptomDao{

    private DBHelper dbHelper;

    public SymptomDaoImpl(Context context) { this.dbHelper = new DBHelper(context); }

    @Override
    public long insert(SymptomModel symptomModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Symptom_TMP.COLUMN_NAME_POS_X, symptomModel.getCirclePosX());
        values.put(Contract.Symptom_TMP.COLUMN_NAME_POS_Y, symptomModel.getCirclePosY());
        values.put(Contract.Symptom_TMP.COLUMN_NAME_CREATION_DATE, symptomModel.getCreationDateTime());
        values.put(Contract.Symptom_TMP.COLUMN_NAME_CIRCLE_RADIUS, symptomModel.getCircleRadius());
        long newId = db.insert(Contract.Symptom_TMP.TABLE_NAME,null, values);
        db.close();
        return newId;
    }

    @Override
    public List<SymptomModel> listAll() throws Exception {
        return null;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return false;
    }

    @Override
    public boolean update(long id, SymptomModel newValues) throws Exception {
        return false;
    }
}
