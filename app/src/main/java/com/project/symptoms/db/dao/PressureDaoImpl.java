package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.PressureModel;

import java.util.ArrayList;
import java.util.List;

public class PressureDaoImpl implements PressureDao {

    private DBHelper dbHelper;

    public PressureDaoImpl(Context context){
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public long insert(PressureModel pressureModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Pressure.COLUMN_NAME_DIASTOLIC, pressureModel.getDiastolic());
        values.put(Contract.Pressure.COLUMN_NAME_SYSTOLIC, pressureModel.getSystolic());
        values.put(Contract.Pressure.COLUMN_NAME_DATE, pressureModel.getDate());
        values.put(Contract.Pressure.COLUMN_NAME_TIME, pressureModel.getTime());
        long newId = db.insert(Contract.Pressure.TABLE_NAME,null, values);
        return newId;
    }

    @Override
    public List<PressureModel> select() throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.Pressure.TABLE_NAME, null, null, null, null, null, null);
        List<PressureModel> result = buildListFromCursor(cursor);
        if (result.size() < 1) throw new Exception("Empty result list");
        return result;
    }

    @Override
    public PressureModel select(long id) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "pressure_id = ?";
        String[] whereArgs = new String[] {""+id};
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        List<PressureModel> result = buildListFromCursor(cursor);
        PressureModel model;
        if (result.size() == 1){ model = result.get(0); }
        else{ throw new Exception("No matching Symptom with id "+id); }
        return model;
    }

    private List<PressureModel> buildListFromCursor(Cursor cursor){
        List<PressureModel> result = new ArrayList<>();
        int id, systolic, diastolic;
        long date, time;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_ID_PK));
            systolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_SYSTOLIC));
            diastolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DIASTOLIC));
            date = cursor.getLong(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DATE));
            time = cursor.getLong(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_TIME));
            result.add(new PressureModel(id, systolic, diastolic, date, time));
        }
        cursor.close();
        return result;
    }

    @Override
    public int delete(long id) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = Contract.Pressure.COLUMN_NAME_ID_PK + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        return db.delete(Contract.Pressure.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public int update(PressureModel pressureModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //New values for one column
        ContentValues values = new ContentValues();
        values.put(Contract.Pressure.COLUMN_NAME_DIASTOLIC, pressureModel.getDiastolic());
        values.put(Contract.Pressure.COLUMN_NAME_SYSTOLIC, pressureModel.getSystolic());
        values.put(Contract.Pressure.COLUMN_NAME_TIME, pressureModel.getTime());
        values.put(Contract.Pressure.COLUMN_NAME_DATE, pressureModel.getDate());

        String selection = Contract.Pressure.COLUMN_NAME_ID_PK + " = ?";
        String[] selectionArgs = {Long.toString(pressureModel.getPressure_id())};
        return db.update(Contract.Pressure.TABLE_NAME, values, selection, selectionArgs);
    }

    public void setDbHelper(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public DBHelper getDbHelper(){
        return dbHelper;
    }

}
