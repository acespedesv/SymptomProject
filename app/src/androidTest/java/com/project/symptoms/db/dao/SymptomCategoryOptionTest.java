package com.project.symptoms.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.dao.SymptomCategoryOptionDao;
import com.project.symptoms.db.dao.SymptomCategoryOptionDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class SymptomCategoryOptionTest {
    private Context appContext;
    private SQLiteDatabase db;
    private SymptomCategoryOptionDao symptomCategoryOptionDao;

    @Before
    public void setUp() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SymptomCategoryOptionDaoImpl symptomCategoryOptionDaoImpl = new SymptomCategoryOptionDaoImpl(appContext);
        DBHelper dbHelper = new DBHelper(appContext);
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        symptomCategoryOptionDaoImpl.setDbHelper(dbHelper);
        symptomCategoryOptionDao = symptomCategoryOptionDaoImpl;
    }

    @Test
    public void givenSymptomCategoryOption_whenInsert_thenReturnGeneratedId() {
        //given
        long id = -1;
        SymptomCategoryOptionModel newSymptomCategoryOption = new SymptomCategoryOptionModel(1, "newCategoryOptionName", 1);

        //when
        try {
            id = symptomCategoryOptionDao.insert(newSymptomCategoryOption);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(id).isNotEqualTo(-1);
    }

    @Test
    public void givenExistingSymptomCategoryOptionName_whenSelectByName_thenReturnSymptomCategoryOption() {
        //given
        String categoryOptionName = "categoryOptionName";
        SymptomCategoryOptionModel categoryOption = new SymptomCategoryOptionModel(1, categoryOptionName, 1);
        SymptomCategoryOptionModel selectedCategoryOption = null;

        try {
            symptomCategoryOptionDao.insert(categoryOption);

            //when
            selectedCategoryOption = symptomCategoryOptionDao.selectSymptomCategoryOption(categoryOptionName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(selectedCategoryOption).isNotNull();
        assertThat(selectedCategoryOption.getCategoryOptionName()).isEqualTo(categoryOptionName);
    }

    @Test
    public void givenExistingSymptomCategoriesOptions_whenListAll_thenReturnList() {
        //given
        SymptomCategoryOptionModel categoryOption1 = new SymptomCategoryOptionModel(1, "option1", 1);
        SymptomCategoryOptionModel categoryOption2 = new SymptomCategoryOptionModel(1, "option2", 1);
        List<SymptomCategoryOptionModel> categoryOptionList = null;

        try {
            symptomCategoryOptionDao.insert(categoryOption1);
            symptomCategoryOptionDao.insert(categoryOption2);

            //when
            categoryOptionList = symptomCategoryOptionDao.listAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(categoryOptionList).isNotNull();
        assertThat(categoryOptionList.size()).isEqualTo(2);
    }

    @Test
    public void givenExistingSymptomCategoryOptionId_whenSelectById_thenReturnSymptomCategoryOption() {
        //given
        long id = -1;
        SymptomCategoryOptionModel categoryOption = new SymptomCategoryOptionModel(1, "existedSymptomCategoryOptionName", 1);
        SymptomCategoryOptionModel selectedCategoryOption = null;

        try {
            id = symptomCategoryOptionDao.insert(categoryOption);

            //when
            selectedCategoryOption = symptomCategoryOptionDao.listById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(selectedCategoryOption).isNotNull();
        assertThat(selectedCategoryOption.getCategoryOptionId()).isEqualTo(id);
        assertThat(selectedCategoryOption.getCategoryFkId()).isEqualTo(1);
        assertThat(selectedCategoryOption.getIconResourceId()).isEqualTo(1);
    }

    @After
    public void cleanUp() {
        db.endTransaction();
    }
}

