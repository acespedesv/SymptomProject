package com.project.symptoms.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.DB.DBHelper;
import com.project.symptoms.DB.Contract;
import com.project.symptoms.Model.Glucose;

public class GlucoseDaoImpl implements GlucoseDao{
    private DBHelper dbHelper;

    public GlucoseDaoImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insert(Glucose glucose) throws Exception {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Contract.Glucose.COLUMN_NAME_VALUE, glucose.getValue());
        values.put(Contract.Glucose.COLUMN_NAME_DATETIME, glucose.getDatetime());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Contract.Glucose.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public boolean update(Glucose glucose) throws Exception {
        return true;
    }

    public int delete(int id) throws Exception{

        // Define 'where' part of query.
        String selection = Contract.Glucose.COLUMN_NAME_ID_PK + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {"" + id};
        // Issue SQL statement.
        //int deletedRows = db.delete(Contract.Glucose.TABLE_NAME, selection, selectionArgs);

        return 0; //deletedRows;
    }

    public boolean select(int id) throws Exception {
        return true;
    }
}
