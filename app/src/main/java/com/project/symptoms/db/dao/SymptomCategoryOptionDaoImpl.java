package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;

import java.util.ArrayList;
import java.util.List;

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
    public SymptomCategoryOptionModel selectSymptomCategoryOption(String name) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "name = ?";
        String[] whereArgs = new String[] {name};
        Cursor cursor = db.query(Contract.CategoryOption.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        SymptomCategoryOptionModel result = buildModelFromCursor(cursor);
        db.close();
        return result;
    }

    @Override
    public List<SymptomCategoryOptionModel> listAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.CategoryOption.TABLE_NAME, null, null, null, null, null, null);
        List<SymptomCategoryOptionModel> result = buildListFromCursor(cursor);
        db.close();
        return result;
    }

    private List<SymptomCategoryOptionModel> buildListFromCursor(Cursor cursor) {
        List<SymptomCategoryOptionModel> result = new ArrayList<>();
        int id, fk;
        String name;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_ID_PK));
            fk = cursor.getInt(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_CATEGORY_ID_FK));
            name = cursor.getString(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_NAME));
            result.add(new SymptomCategoryOptionModel(id, fk, name));
        }
        return result;
    }

    private SymptomCategoryOptionModel buildModelFromCursor(Cursor cursor) {
        SymptomCategoryOptionModel symptomCategoryModel = null;
        int id, fk;
        String name;
        if (cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_ID_PK));
            fk = cursor.getInt(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_CATEGORY_ID_FK));
            name = cursor.getString(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_NAME));
            symptomCategoryModel = new SymptomCategoryOptionModel(id, fk, name);
        }
        return symptomCategoryModel;
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
