package com.project.symptoms.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.project.symptoms.DB.DBHelper;
import com.project.symptoms.DB.DBTables;
import com.project.symptoms.Model.Glucose;

public class GlucoseDao {
    private Context context;
    private SQLiteDatabase db;

    public GlucoseDao(Context context){
        this.context = context;
    }

    public int insert(Glucose glucose){
        DBHelper dbHelper = new DBHelper(context);
        // Gets the data repository in write mode
         db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBTables.Glucose.COLUMN_NAME_VALUE, glucose.getValue());
        values.put(DBTables.Glucose.COLUMN_NAME_DATE, glucose.getDate());
        values.put(DBTables.Glucose.COLUMN_NAME_TIME, glucose.getTime());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBTables.Glucose.TABLE_NAME, null, values);
        db.close();

        return Integer.parseInt(""+newRowId);
    }

    public boolean update(Glucose glucose){
        return true;
    }

    public int delete(int id) {

        // Define 'where' part of query.
        String selection = DBTables.Glucose.COLUMN_NAME_ID_PK+ " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {""+id};
        // Issue SQL statement.
        int deletedRows = db.delete(DBTables.Glucose.TABLE_NAME, selection, selectionArgs);

        return deletedRows;
    }

    public boolean select(Glucose glucose){
        return true;
    }
}
