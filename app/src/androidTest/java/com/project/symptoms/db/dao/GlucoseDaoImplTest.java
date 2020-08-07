package com.project.symptoms.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.GlucoseModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class GlucoseDaoImplTest {

    Context context;
    SQLiteDatabase writableDatabase;
    GlucoseDao dao;

    @Before
    public void setUp(){
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        GlucoseDaoImpl glucoseDao = new GlucoseDaoImpl(context);
        DBHelper helper = new DBHelper(context);
        writableDatabase = helper.getWritableDatabase();
        writableDatabase.beginTransaction(); //Only use transactions in writes because reads won't change anything
        glucoseDao.setDbHelper(helper);

        dao = glucoseDao;
    }

    @Test
    public void givenAModelWhenInsertThenReturnsValidId(){
        try {
            // Given
            GlucoseModel aModel = new GlucoseModel(60, 20, 10);

            // When
            long id = dao.insert(aModel);

            // Then
            assertNotEquals(-1, id);
        }catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void givenAnInsertedModelWhenDeleteThenReturnsOne(){
        try {
            // Given
            GlucoseModel aModel = new GlucoseModel(60, 20, 10);
            long id = dao.insert(aModel);

            // When
            int deletedRows = dao.delete(id);

            // Then
            assertEquals(1, deletedRows);

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void givenAnInsertedModelWhenUpdateThenReturnOne(){
        try {
            // Given
            GlucoseModel model = new GlucoseModel(60, 20, 10);
            long id = dao.insert(model);
            model.setId(id);

            // When
            int updatedRows = dao.update(model);

            // Then
            assertEquals(1, updatedRows);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void givenAnInsertedModelWhenSelectByIdThenReturnAModel(){
        try{
            // Given
            GlucoseModel model = new GlucoseModel(60, 20, 10);
            long id = dao.insert(model);
            model.setId(id);

            // When
            GlucoseModel result = dao.select(id);

            // Then
            assertNotNull(result);
            assertEquals(model.getId(), result.getId());
            assertEquals(model.getValue(), result.getValue());
            assertEquals(model.getDate(), result.getDate());
            assertEquals(model.getTime(), result.getTime());
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void cleanUp(){
        writableDatabase.endTransaction();
    }
}