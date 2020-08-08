package com.project.symptoms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import com.project.symptoms.db.dao.PressureDao;
import com.project.symptoms.db.dao.PressureDaoImpl;
import com.project.symptoms.db.model.PressureModel;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PressureTests {

    private static SQLiteDatabase writableDatabase;
    private static PressureDao dao;
    private static PressureDaoImpl pressureDaoImpl;

    @BeforeClass
    public static void beforeAllTests(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        pressureDaoImpl = new PressureDaoImpl(context);
        writableDatabase = pressureDaoImpl.getDbHelper().getWritableDatabase();
        dao = pressureDaoImpl;
    }

    @Before
    public void beforeEachTest(){
        writableDatabase.beginTransaction();
    }

    @Test
    public void givenAModelWhenInsertThenReturnsValidIdTest(){
        try {
            // Given
            PressureModel aModel = new PressureModel(110, 90, 1034343, 432543);

            // When
            long id = dao.insert(aModel);

            // Then
            assertNotEquals(-1, id);
            assertFalse(writableDatabase.isOpen());

        }catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void givenAnInsertedModelWhenDeleteThenReturnsOneTest(){
        try {
            // Given
            PressureModel aModel = new PressureModel(110, 90, 1034343, 432543);
            long id = dao.insert(aModel);

            // When
            int deletedRows = dao.delete(id);

            // Then
            assertEquals(1, deletedRows);
            assertFalse(writableDatabase.isOpen());

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void givenAnInsertedModelWhenUpdateThenReturnOneTest(){
        try {
            // Given
            PressureModel aModel = new PressureModel(110, 90, 1034343, 432543);
            long id = dao.insert(aModel);
            aModel.setPressure_id(id);

            // When
            int updatedRows = dao.update(aModel);

            // Then
            assertEquals(1, updatedRows);
            assertFalse(writableDatabase.isOpen());

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void givenAnInsertedModelWhenSelectAllThenReturnAListOfModelsTest(){

        try{
            // Given
            int amountOfModels = 5;
            PressureModel model;
            for (int i = 0; i < amountOfModels; i++){
                model = new PressureModel(i+100, i+90, i+10000, i+10000);
                dao.insert(model);
            }

            // When
            List<PressureModel> listOfModels = dao.select();

            // Then
            assertNotNull(listOfModels);
            assertEquals(amountOfModels, listOfModels.size());
            assertFalse(writableDatabase.isOpen());

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void afterEachTest(){
        if(!writableDatabase.isOpen()) writableDatabase = pressureDaoImpl.getDbHelper().getWritableDatabase();
        writableDatabase.endTransaction();
        writableDatabase.close();
    }

}
