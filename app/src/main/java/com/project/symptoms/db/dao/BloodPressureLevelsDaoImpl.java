package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.BloodPressureLevels;
import com.project.symptoms.db.model.PressureModel;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureLevelsDaoImpl implements BloodPressureLevelsDao {
    private DBHelper dbHelper;

    public BloodPressureLevelsDaoImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public long insert(BloodPressureLevels pressureLevel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.PressureLevels.COLUMN_NAME_CATEGORY, pressureLevel.getCategory());
        values.put(Contract.PressureLevels.COLUMN_NAME_SYSTOLIC_MAXIMUM, pressureLevel.getSystolicMaximum());
        values.put(Contract.PressureLevels.COLUMN_NAME_SYSTOLIC_MINIMUM, pressureLevel.getSystolicMinimum());
        values.put(Contract.PressureLevels.COLUMN_NAME_DIASTOLIC_MAXIMUM, pressureLevel.getDiastolicMaximum());
        values.put(Contract.PressureLevels.COLUMN_NAME_DIASTOLIC_MINIMUM, pressureLevel.getDiastolicMinimum());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Contract.PressureLevels.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    @Override
    public List<BloodPressureLevels> listAll() throws Exception {
        List<BloodPressureLevels> result = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.PressureLevels.TABLE_NAME, null, null, null, null, null, null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
        }do {
            assert cursor != null;
            result.add(new BloodPressureLevels(cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5)));
        }while (cursor.moveToNext());
        db.close();
        return result;
    }

    public PressureModel select(int systolic, int diastolic){

        return null;
    }
}
