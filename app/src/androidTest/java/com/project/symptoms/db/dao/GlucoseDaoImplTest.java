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
        writableDatabase.beginTransaction();
        glucoseDao.setDbHelper(helper);

        dao = glucoseDao;
    }

    @Test
    public void testInsert(){
        try {
            long id = dao.insert(new GlucoseModel(60, 20, 10));
            assertNotEquals(-1, id);
        }catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDelete(){
        try {
            long id = dao.insert(new GlucoseModel(99, 20, 10));
            int deletedRows = dao.delete(id);
            assertEquals(1, deletedRows);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testUpdate(){
        try {
            GlucoseModel model = new GlucoseModel(60, 20, 10);
            long id = dao.insert(model);
            model.setId(id);
            int updatedRows = dao.update(model);
            assertEquals(1, updatedRows);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSelectById(){
        try{
            GlucoseModel model = new GlucoseModel(60, 20, 10);
            long id = dao.insert(model);
            model.setId(id);
            GlucoseModel result = dao.select(id);

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