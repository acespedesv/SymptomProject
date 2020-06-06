package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.Pressure;

import java.util.ArrayList;
import java.util.List;

public class PressureDaoImpl implements PressureDao {

    private DBHelper dbHelper;

    public PressureDaoImpl(Context context){
        dbHelper = new DBHelper(context);
    }


    @Override
    public long insert(Pressure pressure) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Pressure.COLUMN_NAME_DIASTOLIC, pressure.getDiastolic());
        values.put(Contract.Pressure.COLUMN_NAME_SYSTOLIC, pressure.getSystolic());
        values.put(Contract.Pressure.COLUMN_NAME_DATETIME, pressure.getDatetime());
        long newId = db.insert(Contract.Pressure.TABLE_NAME,null, values);
        db.close();
        return newId;

    }

    @Override
    public List<Pressure> listAll() throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.Pressure.TABLE_NAME, null, null, null, null, null, null);
        List<Pressure> result = buildListFromCursor(cursor);
        db.close();
        return result;
    }

    List<Pressure> buildListFromCursor(Cursor cursor){
        List<Pressure> result = new ArrayList<>();
        int id, systolic, diastolic;
        long datetime;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_ID_PK));
            systolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_SYSTOLIC));
            diastolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DIASTOLIC));
            datetime = cursor.getLong(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DATETIME));
            result.add(new Pressure(id, systolic, diastolic, datetime));
        }
        return result;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return true;
    }

    @Override
    public boolean update(long id, Pressure newValues) throws Exception {
        return true;
    }

}
