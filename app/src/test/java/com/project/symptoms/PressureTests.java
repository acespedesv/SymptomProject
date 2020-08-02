package com.project.symptoms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.symptoms.db.Contract;
import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.dao.PressureDaoImpl;
import com.project.symptoms.db.model.PressureModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PressureTests {

    private PressureDaoImpl pressureDao;
    private SQLiteDatabase mockDatabase;
    private DBHelper mockDBHelper;

    @Before
    public void setUp(){
        mockDatabase = Mockito.mock(SQLiteDatabase.class);
        mockDBHelper = Mockito.mock(DBHelper.class);
        when(mockDBHelper.getWritableDatabase()).thenReturn(mockDatabase);
        when(mockDBHelper.getReadableDatabase()).thenReturn(mockDatabase);
        pressureDao = new PressureDaoImpl(mockDBHelper);
    }

    /*
    * Insert a pressure Model
    * Check the ID matches
    * Check that the DB object was closed
    * */
    @Test
    public void insertPressureModelTest() {

        // Given
        long expectedId = 10, resultId = 0;
        long someDate = 1596335792, someTime = 1596335792;
        int someSystolicValue = 110, someDiastolicValue = 90;

        // When
        when(mockDatabase.insert(any(), any(), any())).thenReturn(expectedId);
        PressureModel pressureModel = new PressureModel(someSystolicValue, someDiastolicValue, someDate, someTime);
        try {
            resultId = pressureDao.insert(pressureModel);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        // Then
        assertEquals(expectedId, resultId);
        verify(mockDatabase, times(1)).close();
    }

    /*
     * Update a pressure Model
     * Check for affected rows
     * Check that the DB object was closed
     * */
    @Test
    public void updatePressureModelTest(){

        // Given
        int expectedRowsAffected = 1, rowsAffected = 0;
        int pressureId = 10;
        long someDate = 1596335792, someTime = 1596335792;
        int someSystolicValue = 100, someDiastolicValue = 85;

        // When
        when(mockDatabase.update(any(), any(), any(), any())).thenReturn(expectedRowsAffected);
        PressureModel pressureModel = new PressureModel(pressureId, someSystolicValue, someDiastolicValue, someDate, someTime);
        try {
            rowsAffected = pressureDao.update(pressureId, pressureModel);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        // Then
        assertEquals(expectedRowsAffected, rowsAffected);
        verify(mockDatabase, times(1)).close();
    }

    /*
     * Delete a pressure Model
     * Check for affected rows
     * Check that the DB object was closed
     * */
    @Test
    public void deletePressureModelTest(){

        // Given
        int expectedRowsAffected = 1, rowsAffected = 0;
        int pressureId = 10;

        // When
        when(mockDatabase.delete(any(), any(), any())).thenReturn(expectedRowsAffected);
        try {
            rowsAffected = pressureDao.delete(pressureId);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        // Then
        assertEquals(expectedRowsAffected, rowsAffected);
        verify(mockDatabase, times(1)).close();
    }

    /*
     * Retrieve all pressure Models
     * Make the DB return a mocked Cursor
     * Check for the size of the Cursor vs the size of the pressureModel list returned by DAO
     * Check that the DB object was closed
     * Check that Cursor was closed
     * */
    @Test
    public void getAllPressureModelsTest(){

        // Given
        Cursor mockCursor;
        int expectedModelsReturned = 0;
        int pressureId = 10;
        long someDate = 1596335792, someTime = 1596335792;
        int someSystolicValue = 100, someDiastolicValue = 85;
        PressureModel pressureModel = new PressureModel(pressureId, someSystolicValue, someDiastolicValue, someDate, someTime);

        // When
        mockCursor = createMockCursorForSelectTest(new PressureModel[] {pressureModel});
        when(mockDatabase.query(any(), any(), any(), any(), any(), any(), any())).thenReturn(mockCursor);
        try {
            expectedModelsReturned = pressureDao.listAll().size();
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        // Then
        assertEquals(mockCursor.getCount(), expectedModelsReturned);
        verify(mockCursor, times(1)).close();
        verify(mockDatabase, times(1)).close();

    }

    /**
     * Return a mocked cursor that behaves as if it's representing the parameters
     */
    public Cursor createMockCursorForSelectTest(PressureModel modelsToReturn[]){

        String[] modelFields = {Contract.Pressure.COLUMN_NAME_ID_PK,
                Contract.Pressure.COLUMN_NAME_SYSTOLIC,
                Contract.Pressure.COLUMN_NAME_DIASTOLIC,
                Contract.Pressure.COLUMN_NAME_TIME,
                Contract.Pressure.COLUMN_NAME_DATE};

        Cursor mockCursor = Mockito.mock(Cursor.class);

        for (int i = 0; i < modelFields.length; i++) {
            when(mockCursor.getColumnIndex(modelFields[i])).thenReturn(i);
        }

        if(modelsToReturn.length >= 1)
            when(mockCursor.moveToFirst()).thenReturn(true); // do this only if not received empty array

        for (PressureModel each : modelsToReturn) {
            when(mockCursor.getInt(0)).thenReturn(each.getPressure_id());
            when(mockCursor.getInt(1)).thenReturn(each.getSystolic());
            when(mockCursor.getInt(2)).thenReturn(each.getDiastolic());
            when(mockCursor.getLong(3)).thenReturn(each.getDate());
            when(mockCursor.getLong(4)).thenReturn(each.getTime());
        }

        return mockCursor;
    }

}
