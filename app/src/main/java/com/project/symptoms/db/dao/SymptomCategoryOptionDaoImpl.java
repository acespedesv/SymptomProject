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
        values.put(Contract.CategoryOption.COLUMN_NAME_CATEGORY_ID_FK, symptomCategoryOptionModel.getCategoryFkId());
        values.put(Contract.CategoryOption.COLUMN_NAME_NAME, symptomCategoryOptionModel.getCategoryOptionName());
        values.put(Contract.CategoryOption.COLUMN_NAME_ICON_RESOURCE_ID, symptomCategoryOptionModel.getIconResourceId());
        long newId = db.insert(Contract.CategoryOption.TABLE_NAME,null, values);
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

    @Override
    public SymptomCategoryOptionModel listById(long categoryOptionId) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "category_option_id = ?";
        String[] whereArgs = new String[] {Long.toString(categoryOptionId)};
        Cursor cursor = db.query(Contract.CategoryOption.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        SymptomCategoryOptionModel result = buildModelFromCursor(cursor);
        db.close();
        return result;
    }

    private List<SymptomCategoryOptionModel> buildListFromCursor(Cursor cursor) {
        List<SymptomCategoryOptionModel> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(buildModelFromCursor(cursor));
        }
        return result;
    }

    /**
     * Assumes moveToNext() already called
     */
    private SymptomCategoryOptionModel buildModelFromCursor(Cursor cursor) {
        SymptomCategoryOptionModel symptomCategoryOptionModel;
        int id, fk, iconResourceId;
        String name;
        id = cursor.getInt(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_ID_PK));
        fk = cursor.getInt(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_CATEGORY_ID_FK));
        name = cursor.getString(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_NAME));
        iconResourceId = cursor.getInt(cursor.getColumnIndex(Contract.CategoryOption.COLUMN_NAME_ICON_RESOURCE_ID));
        symptomCategoryOptionModel = new SymptomCategoryOptionModel(id, fk, name, iconResourceId);
        return symptomCategoryOptionModel;
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
