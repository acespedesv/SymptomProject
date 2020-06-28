package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SymptomCategoryModel;

public class SymptomCategoryDaoImpl implements SymptomCategoryDao {

    private DBHelper dbHelper;

    public SymptomCategoryDaoImpl(Context context) { this.dbHelper = new DBHelper(context); }

    @Override
    public long insert(SymptomCategoryModel symptomCategoryModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Category.COLUMN_NAME_ID_PK, symptomCategoryModel.getCategoryId());
        values.put(Contract.Category.COLUMN_NAME_NAME, symptomCategoryModel.getName());
        long newId = db.insert(Contract.Category.TABLE_NAME,null, values);
        db.close();
        return newId;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }

    @Override
    public boolean update(int id, SymptomCategoryModel newValues) throws Exception {
        return false;
    }
}
