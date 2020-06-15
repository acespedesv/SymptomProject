package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.Glucose;

public class GlucoseDaoImpl implements GlucoseDao {
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

    public int update(Glucose glucose) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //New value for one column
        ContentValues values = new ContentValues();
        values.put(Contract.Glucose.COLUMN_NAME_VALUE, glucose.getValue());
        values.put(Contract.Glucose.COLUMN_NAME_DATETIME, glucose.getDatetime());

        String selection = Contract.Glucose.COLUMN_NAME_ID_PK + " = ?";
        String[] selectionArgs = {Integer.toString(glucose.getId())};

        int count = db.update(Contract.Glucose.TABLE_NAME, values, selection, selectionArgs);
        return count;
    }

    public int delete(int id) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = Contract.Glucose.COLUMN_NAME_ID_PK + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {Integer.toString(id)};
        // Issue SQL statement.
        return db.delete(Contract.Glucose.TABLE_NAME, selection, selectionArgs); //deletedRows;
    }

    public Glucose select(int id) throws Exception {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                //BaseColumns._ID,
                Contract.Glucose.COLUMN_NAME_VALUE,
                Contract.Glucose.COLUMN_NAME_DATETIME
        };

        String selection = Contract.Glucose.COLUMN_NAME_ID_PK + " = ?";
        String[] selectionArgs = {"" + id};

        /*String sortOrder =
                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";*/

        Cursor cursor = db.query(
                Contract.Glucose.TABLE_NAME,   // The table to query
                columns,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                  // don't filter by row groups
                null               // The sort order
        );

        cursor.moveToFirst();
        String value = cursor.getString(0);
        String dateTime = cursor.getString(1);
        return new Glucose(id, Integer.parseInt(value), Long.parseLong(dateTime));
    }
}
