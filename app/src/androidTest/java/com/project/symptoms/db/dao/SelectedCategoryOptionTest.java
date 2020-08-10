package com.project.symptoms.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.model.SelectedCategoryOptionModel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SelectedCategoryOptionTest {
    Context appContext;
    SelectedCategoryOptionDao selectedCategoryOptionDao;
    SQLiteDatabase db;

    @Before
    public void setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SelectedCategoryOptionDaoImpl selectedCategoryOptionDaoImpl = new SelectedCategoryOptionDaoImpl(appContext);
        DBHelper dbHelper = new DBHelper(appContext);
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        selectedCategoryOptionDaoImpl.setDbHelper(dbHelper);
        selectedCategoryOptionDao = selectedCategoryOptionDaoImpl;
    }

    @Test
    public void givenSelectedCategoryOption_whenInsert_thenReturnGeneratedId() {
        //given
        long id = -1;
        SelectedCategoryOptionModel newSelectedCategoryOptionModel = new SelectedCategoryOptionModel(100, 100);

        //when
        try {
            id = selectedCategoryOptionDao.insert(newSelectedCategoryOptionModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(id).isNotEqualTo(-1);
    }

    @Test
    public void givenExistedSymptomId_whenSelectAllBySymptom_thenReturnList() {
        List<SelectedCategoryOptionModel> selectedCategoryOptionModelList = null;
        try {
            //given
            for (int i = 0; i <= 5; i++) {
                SelectedCategoryOptionModel selectedCategoryOptionModel = new SelectedCategoryOptionModel(10, 20);
                selectedCategoryOptionDao.insert(selectedCategoryOptionModel);
            }
            //When
            selectedCategoryOptionModelList = selectedCategoryOptionDao.selectBySymptomId(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //then
        assertThat(selectedCategoryOptionModelList).isNotNull();
    }

    @Test
    public void givenExistedSelectedCategoryOptions_whenSelectAll_thenReturnList() {
        List<SelectedCategoryOptionModel> selectedCategoryOptionModelList = null;
        try {
            //given
            for (int i = 0; i <= 5; i++) {
                SelectedCategoryOptionModel selectedCategoryOptionModel = new SelectedCategoryOptionModel(10, 20);
                selectedCategoryOptionDao.insert(selectedCategoryOptionModel);
            }
            //When
            selectedCategoryOptionModelList = selectedCategoryOptionDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //then
        assertThat(selectedCategoryOptionModelList).isNotNull();
    }

    @Test
    public void givenExistedSelectedCategoryOption_whenDelete_thenReturnRowsDeleted() {
        //given
        int rowsDeleted = -1;
        long symptomId = 1;
        long categoryOptionId = 2;
        SelectedCategoryOptionModel selectedCategoryOptionModel = new SelectedCategoryOptionModel(symptomId, categoryOptionId);
        //when
        try {
            selectedCategoryOptionDao.insert(selectedCategoryOptionModel);
            rowsDeleted = selectedCategoryOptionDao.delete(symptomId, categoryOptionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //then
        assertThat(rowsDeleted).isEqualTo(1);
    }
    @Test
    public void givenExistedSelectedCategoryOption_whenDeleteBySymptomId_thenReturnRowsDeleted() {
        //given
        int rowsDeleted = -1;
        long symptomId = 1;
        long categoryOptionId = 2;
        SelectedCategoryOptionModel selectedCategoryOptionModel = new SelectedCategoryOptionModel(symptomId, categoryOptionId);
        //when
        try {
            selectedCategoryOptionDao.insert(selectedCategoryOptionModel);
            rowsDeleted = selectedCategoryOptionDao.deleteBySymptomId(symptomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //then
        assertThat(rowsDeleted).isEqualTo(1);
    }

    @After
    public void cleanUp() {
        db.endTransaction();
    }
}