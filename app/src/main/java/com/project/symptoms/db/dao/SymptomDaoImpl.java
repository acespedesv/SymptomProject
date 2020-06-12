package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SymptomModel;

import java.util.ArrayList;
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
        values.put(Contract.Symptom_TMP.COLUMN_NAME_CREATION_DATE, symptomModel.getCreationDate());
        values.put(Contract.Symptom_TMP.COLUMN_NAME_CREATION_DATE, symptomModel.getCreationTime());
        values.put(Contract.Symptom_TMP.COLUMN_NAME_CIRCLE_RADIUS, symptomModel.getCircleRadius());
        long newId = db.insert(Contract.Symptom_TMP.TABLE_NAME,null, values);
        db.close();
        return newId;
    }

    private List<SymptomModel> buildListFromCursor(Cursor cursor){
        List<SymptomModel> result = new ArrayList<>();
        int id;
        float circlePosX, circlePosY, circleRadius;
        long date, time;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(Contract.Symptom_TMP.COLUMN_NAME_ID_PK));
            circlePosX = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom_TMP.COLUMN_NAME_POS_X));
            circlePosY = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom_TMP.COLUMN_NAME_POS_Y));
            circleRadius = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom_TMP.COLUMN_NAME_POS_Y));
            date = cursor.getLong(cursor.getColumnIndex(Contract.Symptom_TMP.COLUMN_NAME_CREATION_DATE));
            time = cursor.getLong(cursor.getColumnIndex(Contract.Symptom_TMP.COLUMN_NAME_CREATION_TIME));
            result.add(new SymptomModel(id, circlePosX, circlePosY, date, time, circleRadius));
        }
        return result;
    }

    @Override
    public List<SymptomModel> listAll() throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.Symptom_TMP.TABLE_NAME, null, null, null, null, null, null);
        List<SymptomModel> result = buildListFromCursor(cursor);
        db.close();
        return result;
    }

    @Override
    public List<SymptomModel> listAll(long dateTime) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "creation_date = ?";
        String[] whereArgs = new String[] {Long.toString(dateTime)};
        Cursor cursor = db.query(Contract.Symptom_TMP.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        List<SymptomModel> result = buildListFromCursor(cursor);
        db.close();
        return result;
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
