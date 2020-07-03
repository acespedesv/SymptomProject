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
        values.put(Contract.Symptom.COLUMN_NAME_POS_X, symptomModel.getCirclePosX());
        values.put(Contract.Symptom.COLUMN_NAME_POS_Y, symptomModel.getCirclePosY());
        values.put(Contract.Symptom.COLUMN_NAME_SIDE, symptomModel.getCircleSide());
        values.put(Contract.Symptom.COLUMN_NAME_START_TIME, symptomModel.getStartTime());
        values.put(Contract.Symptom.COLUMN_NAME_END_TIME, symptomModel.getEndTime());
        values.put(Contract.Symptom.COLUMN_NAME_CIRCLE_RADIUS, symptomModel.getCircleRadius());
        long newId = db.insert(Contract.Symptom.TABLE_NAME,null, values);
        db.close();
        return newId;
    }

    private List<SymptomModel> buildListFromCursor(Cursor cursor){
        List<SymptomModel> result = new ArrayList<>();
        int id;
        float circlePosX, circlePosY, circleRadius;
        long startTime, endTime;
        int circleSide;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_ID_PK));
            circlePosX = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_POS_X));
            circlePosY = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_POS_Y));
            circleSide = cursor.getInt(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_SIDE));
            circleRadius = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_CIRCLE_RADIUS));
            startTime = cursor.getLong(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_START_TIME));
            endTime = cursor.getLong(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_END_TIME));
            result.add(new SymptomModel(id, circlePosX, circlePosY, startTime, endTime, circleRadius, circleSide));
        }
        return result;
    }

    @Override
    public List<SymptomModel> listAll() throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, null, null, null, null, null);
        List<SymptomModel> result = buildListFromCursor(cursor);
        db.close();
        return result;
    }

    @Override
    public List<SymptomModel> listAll(long dateTime, int circleSide) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int milisecondsInDay = 1000*60*60*24;
        String whereClause = "? <= start_time AND start_time <= ? AND circle_side = ?";
        String lowerDate = Long.toString(dateTime);
        String upperDate = Long.toString(dateTime+milisecondsInDay);
        String[] whereArgs = new String[] {lowerDate, upperDate, Integer.toString(circleSide)};
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
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

    @Override
    public SymptomModel getById(long id) throws Exception{
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "symtom_id = ?";
        String[] whereArgs = new String[] {""+id};
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, whereClause, whereArgs, null, null, null);

        List<SymptomModel> result = buildListFromCursor(cursor);
        SymptomModel model;
        if (result.size() == 1){
            model = result.get(0);
        }
        else{
            throw new Exception("No matching Symptom with id "+id);
        }
        db.close();
        return model;
    }
}
