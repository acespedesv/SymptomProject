package com.project.symptoms.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.db.model.SymptomViewModel;

import java.util.ArrayList;
import java.util.List;

public class SymptomDaoImpl implements SymptomDao {

    private DBHelper dbHelper;

    public SymptomDaoImpl(Context context) { this.dbHelper = new DBHelper(context); }

    @Override
    public long insert(SymptomModel symptomModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Symptom.COLUMN_NAME_DESCRIPTION, symptomModel.getDescription());
        values.put(Contract.Symptom.COLUMN_NAME_INTENSITY, symptomModel.getIntensity());
        values.put(Contract.Symptom.COLUMN_NAME_INTERMITTENCE, symptomModel.getIntermittence());
        values.put(Contract.Symptom.COLUMN_NAME_START_DATE, symptomModel.getStartDate());
        values.put(Contract.Symptom.COLUMN_NAME_START_TIME, symptomModel.getStartTime());
        values.put(Contract.Symptom.COLUMN_NAME_DURATION, symptomModel.getDuration());
        values.put(Contract.Symptom.COLUMN_NAME_CAUSING_DRUG, symptomModel.getCausingDrug());
        values.put(Contract.Symptom.COLUMN_NAME_CAUSING_FOOD, symptomModel.getCausingFood());
        values.put(Contract.Symptom.COLUMN_NAME_POS_X, symptomModel.getCirclePosX());
        values.put(Contract.Symptom.COLUMN_NAME_POS_Y, symptomModel.getCirclePosY());
        values.put(Contract.Symptom.COLUMN_NAME_SIDE, symptomModel.getCircleSide());
        values.put(Contract.Symptom.COLUMN_NAME_CIRCLE_RADIUS, symptomModel.getCircleRadius());
        long newId = db.insert(Contract.Symptom.TABLE_NAME,null, values);
        db.close();
        return newId;
    }

    @Override
    public List<SymptomModel> listAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, null, null, null, null, null);
        List<SymptomModel> result = buildListFromCursor(cursor);
        db.close();
        return result;
    }

    @Override
    public List<SymptomModel> listAll(long dateTime, int circleSide) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "start_date = ? AND circle_side = ?";
        String[] whereArgs = new String[] {Long.toString(dateTime), Integer.toString(circleSide)};
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        List<SymptomModel> result = buildListFromCursor(cursor);
        db.close();
        return result;
    }

    @Override
    public List<SymptomModel> select(long initialDate, long finalDate) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = Contract.Symptom.COLUMN_NAME_START_DATE + " BETWEEN ? AND ?";
        String[] whereArgs = new String[] {Long.toString(initialDate), Long.toString(finalDate)};
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        List<SymptomModel> result;
        result = buildListFromCursor(cursor);
        cursor.close();
        db.close();
        return result;
    }

    @Override
    public List<SymptomViewModel> selectFromView(long symptomId) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = Contract.SymptomView.COLUMN_NAME_SYMPTOM_ID + " = ?";
        String[] whereArgs = new String[] {Long.toString(symptomId)};
        Cursor cursor = db.query(Contract.SymptomView.VIEW_NAME, null, whereClause, whereArgs, null, null, null);
        List<SymptomViewModel> result;
        result = buildSymptomViewFromCursor(cursor);
        cursor.close();
        db.close();
        return result;
    }

    private List<SymptomViewModel> buildSymptomViewFromCursor(Cursor cursor){
        List<SymptomViewModel> result = new ArrayList<>();
        long symptomId;
        long categoryOptionId;
        String categoryOptionName;
        long categoryId;
        String categoryName;

        while(cursor.moveToNext()){

            symptomId = cursor.getLong(cursor.getColumnIndex(Contract.SymptomView.COLUMN_NAME_SYMPTOM_ID));
            categoryOptionId = cursor.getLong(cursor.getColumnIndex(Contract.SymptomView.COLUMN_NAME_CATEGORY_OPTION_ID));
            categoryOptionName = cursor.getString(cursor.getColumnIndex(Contract.SymptomView.COLUMN_NAME_CATEGORY_OPTION_NAME));
            categoryId = cursor.getLong(cursor.getColumnIndex(Contract.SymptomView.COLUMN_NAME_CATEGORY_ID));
            categoryName = cursor.getString(cursor.getColumnIndex(Contract.SymptomView.COLUMN_NAME_CATEGORY_NAME));

            SymptomViewModel model = new SymptomViewModel(
                    symptomId,
                    categoryOptionId,
                    categoryOptionName,
                    categoryId,
                    categoryName
            );

            result.add(model);

        }
        return result;
    }

    private List<SymptomModel> buildListFromCursor(Cursor cursor){
        List<SymptomModel> result = new ArrayList<>();
        int id;
        float circlePosX, circlePosY, circleRadius;
        String description, intensity, causingDrug, causingFood;
        int intermittence;
        long startDate, startTime;
        int circleSide;
        int duration;
        while(cursor.moveToNext()){

            id = cursor.getInt(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_ID_PK));
            description = cursor.getString(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_DESCRIPTION));
            intensity = cursor.getString(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_INTENSITY));
            causingDrug = cursor.getString(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_CAUSING_DRUG));
            causingFood = cursor.getString(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_CAUSING_FOOD));
            intermittence = cursor.getInt(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_INTERMITTENCE));
            circlePosX = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_POS_X));
            circlePosY = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_POS_Y));
            circleSide = cursor.getInt(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_SIDE));
            circleRadius = cursor.getFloat(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_CIRCLE_RADIUS));
            startDate = cursor.getLong(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_START_DATE));
            startTime = cursor.getLong(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_START_TIME));
            duration = cursor.getInt(cursor.getColumnIndex(Contract.Symptom.COLUMN_NAME_DURATION));

            SymptomModel model = new SymptomModel();
            model.setSymptomId(id);
            model.setCirclePosX(circlePosX);
            model.setCirclePosY(circlePosY);
            model.setCircleRadius(circleRadius);
            model.setCircleSide(circleSide);
            model.setStartDate(startDate);
            model.setStartTime(startTime);
            model.setDuration(duration);
            model.setIntensity(intensity);
            model.setDescription(description);
            model.setCausingDrug(causingDrug);
            model.setCausingFood(causingFood);
            model.setIntermittence(intermittence);

            result.add(model);

        }
        return result;
    }

    @Override
    public boolean delete(long id) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = Contract.Symptom.COLUMN_NAME_ID_PK + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        return db.delete(Contract.Symptom.TABLE_NAME, selection, selectionArgs) != -1;
    }

    @Override
    public boolean update(long id, SymptomModel symptomModel) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //New values for one column
        ContentValues values = new ContentValues();
        values.put(Contract.Symptom.COLUMN_NAME_DESCRIPTION, symptomModel.getDescription());
        values.put(Contract.Symptom.COLUMN_NAME_INTENSITY, symptomModel.getIntensity());
        values.put(Contract.Symptom.COLUMN_NAME_INTERMITTENCE, symptomModel.getIntermittence());
        values.put(Contract.Symptom.COLUMN_NAME_START_DATE, symptomModel.getStartDate());
        values.put(Contract.Symptom.COLUMN_NAME_START_TIME, symptomModel.getStartTime());
        values.put(Contract.Symptom.COLUMN_NAME_DURATION, symptomModel.getDuration());
        values.put(Contract.Symptom.COLUMN_NAME_CAUSING_DRUG, symptomModel.getCausingDrug());
        values.put(Contract.Symptom.COLUMN_NAME_CAUSING_FOOD, symptomModel.getCausingFood());
        values.put(Contract.Symptom.COLUMN_NAME_POS_X, symptomModel.getCirclePosX());
        values.put(Contract.Symptom.COLUMN_NAME_POS_Y, symptomModel.getCirclePosY());
        values.put(Contract.Symptom.COLUMN_NAME_SIDE, symptomModel.getCircleSide());
        values.put(Contract.Symptom.COLUMN_NAME_CIRCLE_RADIUS, symptomModel.getCircleRadius());

        String selection = Contract.Symptom.COLUMN_NAME_ID_PK + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        int count = db.update(Contract.Symptom.TABLE_NAME, values, selection, selectionArgs);
        return count >= 1;
    }

    @Override
    public SymptomModel getById(long id) throws Exception{
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "symptom_id = ?";
        String[] whereArgs = new String[] {""+id};
        Cursor cursor = db.query(Contract.Symptom.TABLE_NAME, null, whereClause, whereArgs, null, null, null);

        List<SymptomModel> result = buildListFromCursor(cursor);
        SymptomModel model;
        if (result.size() == 1){
            model = result.get(0);
        }
        else{
            throw new Exception("No matching Symptom with id "+id);
        }
        db.close();
        return model;
    }
}
