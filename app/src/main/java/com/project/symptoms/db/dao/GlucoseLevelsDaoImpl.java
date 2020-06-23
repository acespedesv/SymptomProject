package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.GlucoseLevels;

import java.util.ArrayList;
import java.util.List;

public class GlucoseLevelsDaoImpl implements GlucoseLevelsDao {
    private DBHelper dbHelper;

    public GlucoseLevelsDaoImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insert(GlucoseLevels glucoseLevels) throws Exception {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Contract.GlucoseLevels.COLUMN_NAME_LEVEL, glucoseLevels.getLevel());
        values.put(Contract.GlucoseLevels.COLUMN_NAME_FASTING_PLASMA, glucoseLevels.getFastingPlasma());
        values.put(Contract.GlucoseLevels.COLUMN_NAME_TOLERANCE_TEST, glucoseLevels.getToleranceTest());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Contract.GlucoseLevels.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public List<GlucoseLevels> listAll(){
        List<GlucoseLevels> result = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Contract.GlucoseLevels.TABLE_NAME, null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
        }do {
            result.add(new GlucoseLevels(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));
        }while (cursor.moveToNext());
      
        return result;  
    }


}
