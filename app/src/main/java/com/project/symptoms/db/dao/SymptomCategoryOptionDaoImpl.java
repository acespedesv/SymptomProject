package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;

public class SymptomCategoryOptionDaoImpl implements SymptomCategoryOptionDao {

    private DBHelper dbHelper;

    public SymptomCategoryOptionDaoImpl(Context context) { this.dbHelper = new DBHelper(context); }

    @Override
    public long insert(SymptomCategoryOptionModel symptomCategoryOptionModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.CategoryOption.COLUMN_NAME_ID_PK, symptomCategoryOptionModel.getCategoryOptionId());
        values.put(Contract.CategoryOption.COLUMN_NAME_CATEGORY_ID_FK, symptomCategoryOptionModel.getCategoryFkId());
        values.put(Contract.CategoryOption.COLUMN_NAME_NAME, symptomCategoryOptionModel.getCategoryOptionName());
        long newId = db.insert(Contract.Category.TABLE_NAME,null, values);
        db.close();
        return newId;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }

    @Override
    public boolean update(int id, SymptomCategoryOptionModel newValues) throws Exception {
        return false;
    }
}
