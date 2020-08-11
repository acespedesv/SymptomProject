package com.project.symptoms.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.project.symptoms.db.DBHelper;
import com.project.symptoms.db.dao.SymptomCategoryDao;
import com.project.symptoms.db.dao.SymptomCategoryDaoImpl;
import com.project.symptoms.db.model.SymptomCategoryModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class SymptomCategoryTest{
    private Context appContext;
    private SQLiteDatabase db;
    private SymptomCategoryDao symptomCategoryDao;

    @Before
    public void setUp(){
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SymptomCategoryDaoImpl symptomCategoryDaoImpl = new SymptomCategoryDaoImpl(appContext);
        DBHelper dbHelper = new DBHelper(appContext);
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        symptomCategoryDaoImpl.setDbHelper(dbHelper);
        symptomCategoryDao = symptomCategoryDaoImpl;
    }

    @Test
    public void givenCategory_whenInsert_thenReturnGeneratedId(){
        //given
        SymptomCategoryModel newSymptomCategory = new SymptomCategoryModel(1, "newSymptomCategory");
        long id = -1;

        //when
        try {
             id = symptomCategoryDao.insert(newSymptomCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
       assertThat(id).isNotEqualTo(-1);
    }

    @Test
    public void givenExistingCategory_whenSelectCategoryByName_thenReturnCategory(){
        //given
        String CategoryName = "CategoryName";
        SymptomCategoryModel Category = new SymptomCategoryModel(CategoryName);
        SymptomCategoryModel selectedCategory = null;

        try {
            symptomCategoryDao.insert(Category);

            //when
            selectedCategory = symptomCategoryDao.selectSymptomCategory(CategoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(selectedCategory).isNotNull();
        assertThat(CategoryName).isEqualTo(selectedCategory.getName());
    }

    @Test
    public void givenExistingCategories_whenListAll_thenReturnList(){
        //given
        SymptomCategoryModel Category1 = new SymptomCategoryModel("CategoryName1");
        SymptomCategoryModel Category2 = new SymptomCategoryModel("CategoryName2");
        List<SymptomCategoryModel> CategoryList = null;

        try {
            symptomCategoryDao.insert(Category1);
            symptomCategoryDao.insert(Category2);

            //when
            CategoryList  = symptomCategoryDao.listAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(CategoryList).isNotNull();
    }

    @After
    public void cleanUp(){
        db.endTransaction();
    }
}
