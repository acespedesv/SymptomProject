package com.project.symptoms.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SympDB.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Contract.SQL_CREATE_SYMPTOM_TMP);
        db.execSQL(Contract.SQL_CREATE_SYMPTOM);
        db.execSQL(Contract.SQL_CREATE_CATEGORY);
        db.execSQL(Contract.SQL_CREATE_CATEGORY_OPTION);
        db.execSQL(Contract.SQL_CREATE_SELECTED_CATEGORY_OPTION);
        db.execSQL(Contract.SQL_CREATE_PRESSURE);
        db.execSQL(Contract.SQL_CREATE_GLUCOSE);
        db.execSQL(Contract.SQL_CREATE_GLUCOSE_LEVELS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Contract.SQL_DELETE_SYMPTOM_TMP);
        db.execSQL(Contract.SQL_DELETE_SYMPTOM);
        db.execSQL(Contract.SQL_DELETE_CATEGORY);
        db.execSQL(Contract.SQL_DELETE_CATEGORY_OPTION);
        db.execSQL(Contract.SQL_DELETE_SELECTED_CATEGORY_OPTION);
        db.execSQL(Contract.SQL_DELETE_PRESSURE);
        db.execSQL(Contract.SQL_DELETE_GLUCOSE);
        db.execSQL(Contract.SQL_DELETE_GLUCOSE_LEVELS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
