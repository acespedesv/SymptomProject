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
        dbHelper = new DBHelper(context);
    }


    @Override
    public long insert(PressureModel pressureModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Pressure.COLUMN_NAME_DIASTOLIC, pressureModel.getDiastolic());
        values.put(Contract.Pressure.COLUMN_NAME_SYSTOLIC, pressureModel.getSystolic());
        values.put(Contract.Pressure.COLUMN_NAME_DATETIME, pressureModel.getDatetime());
        long newId = db.insert(Contract.Pressure.TABLE_NAME,null, values);
        db.close();
        return newId;

    }

    @Override
    public List<PressureModel> listAll() throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.Pressure.TABLE_NAME, null, null, null, null, null, null);
        List<PressureModel> result = buildListFromCursor(cursor);
        db.close();
        return result;
    }

    List<PressureModel> buildListFromCursor(Cursor cursor){
        List<PressureModel> result = new ArrayList<>();
        int id, systolic, diastolic;
        long datetime;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_ID_PK));
            systolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_SYSTOLIC));
            diastolic = cursor.getInt(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DIASTOLIC));
            datetime = cursor.getLong(cursor.getColumnIndex(Contract.Pressure.COLUMN_NAME_DATETIME));
            result.add(new PressureModel(id, systolic, diastolic, datetime));
        }
        return result;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return true;
    }

    @Override
    public boolean update(long id, PressureModel newValues) throws Exception {
        return true;
    }

}
