package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public List<PressureModel> selectAll() throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sortOrder =
                Contract.Pressure.COLUMN_NAME_DATE + "+"+
                        Contract.Pressure.COLUMN_NAME_TIME + " DESC";
        Cursor cursor = db.query(Contract.Pressure.TABLE_NAME, null, null, null, null, null, sortOrder);
        List<PressureModel> result = buildListFromCursor(cursor);
        if (result.size() < 1) throw new Exception("Empty result list");
        return result;
    }

    private List<PressureModel> buildListFromCursor(Cursor cursor){
        List<PressureModel> result = new ArrayList<>();
        while(cursor.moveToNext()){
            result.add(buildModelFromCursor(cursor));
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
        String[] selectionArgs = {Long.toString(pressureModel.getId())};
        return db.update(Contract.Pressure.TABLE_NAME, values, selection, selectionArgs);
    }

    public void setDbHelper(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public DBHelper getDbHelper(){
        return dbHelper;
    }

    @Override
    public PressureModel select(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = Contract.Pressure.COLUMN_NAME_ID_PK + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        Cursor cursor = db.query(Contract.Pressure.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        cursor.moveToNext();
        return buildModelFromCursor(cursor);
    }

    @Override
    public List<PressureModel> select(long initialDate, long finalDate) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = Contract.Pressure.COLUMN_NAME_DATE + " BETWEEN ? AND ?";
        Log.e("LOG", "Pressure dates: " + initialDate + ", " + finalDate);
        String[] whereArgs = new String[] {Long.toString(initialDate), Long.toString(finalDate)};
        Cursor cursor = db.query(Contract.Pressure.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        List<PressureModel> result = new ArrayList<>();
        while(cursor.moveToNext()){
            result.add(buildModelFromCursor(cursor));
        }
        cursor.close();
        db.close();
        return result;
    }

    private PressureModel buildModelFromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_ID_PK));
        int systolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_SYSTOLIC));
        int diastolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DIASTOLIC));
        long date = cursor.getLong(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DATE));
        long time = cursor.getLong(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_TIME));
        return new PressureModel(id, systolic, diastolic, date, time);
    }


}
