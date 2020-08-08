package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SymptomCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class SymptomCategoryDaoImpl implements SymptomCategoryDao {

    private DBHelper dbHelper;

    public SymptomCategoryDaoImpl(Context context) { this.dbHelper = new DBHelper(context); }

    @Override
    public long insert(SymptomCategoryModel symptomCategoryModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Category.COLUMN_NAME_NAME, symptomCategoryModel.getName());
        long newId = db.insert(Contract.Category.TABLE_NAME,null, values);
//        db.close();
        return newId;
    }

    @Override
    public SymptomCategoryModel selectSymptomCategory(String name) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "name = ?";
        String[] whereArgs = new String[] {name};
        Cursor cursor = db.query(Contract.Category.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        SymptomCategoryModel result = buildModelFromCursor(cursor);
//        db.close();
        return result;
    }

    @Override
    public List<SymptomCategoryModel> listAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.Category.TABLE_NAME, null, null, null, null, null, null);
        List<SymptomCategoryModel> result = buildListFromCursor(cursor);
//        db.close();
        return result;
    }

    private List<SymptomCategoryModel> buildListFromCursor(Cursor cursor) {
        List<SymptomCategoryModel> result = new ArrayList<>();
        int id;
        String name;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(Contract.Category.COLUMN_NAME_ID_PK));
            name = cursor.getString(cursor.getColumnIndex(Contract.Category.COLUMN_NAME_NAME));
            result.add(new SymptomCategoryModel(id, name));
        }
        return result;
    }

    private SymptomCategoryModel buildModelFromCursor(Cursor cursor) {
        SymptomCategoryModel symptomCategoryModel = null;
        int id;
        String name;
        if (cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex(Contract.Category.COLUMN_NAME_ID_PK));
            name = cursor.getString(cursor.getColumnIndex(Contract.Category.COLUMN_NAME_NAME));
            symptomCategoryModel = new SymptomCategoryModel(id, name);
        }
        return symptomCategoryModel;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return false;
    }

    @Override
    public boolean update(long id, SymptomCategoryModel newValues) throws Exception {
        return false;
    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
