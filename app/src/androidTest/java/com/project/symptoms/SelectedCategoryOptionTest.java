package com.project.symptoms;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.project.symptoms.db.dao.SelectedCategoryOptionDao;
import com.project.symptoms.db.dao.SelectedCategoryOptionDaoImpl;
import com.project.symptoms.db.dao.SymptomCategoryDao;
import com.project.symptoms.db.dao.SymptomCategoryDaoImpl;
import com.project.symptoms.db.dao.SymptomCategoryOptionDao;
import com.project.symptoms.db.dao.SymptomCategoryOptionDaoImpl;
import com.project.symptoms.db.dao.SymptomDao;
import com.project.symptoms.db.dao.SymptomDaoImpl;
import com.project.symptoms.db.model.SelectedCategoryOptionModel;
import com.project.symptoms.db.model.SymptomCategoryModel;
import com.project.symptoms.db.model.SymptomCategoryOptionModel;
import com.project.symptoms.db.model.SymptomModel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SelectedCategoryOptionTest {
    Context appContext;
    SelectedCategoryOptionDao selectedCategoryOptionDao;

    @Before
    public void setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        selectedCategoryOptionDao = new SelectedCategoryOptionDaoImpl(appContext);
    }

    @Test
    public void givenSelectedCategoryOption_whenInsert_thenReturnGeneratedId() {
        //given
        long id = -1;
        long idSymptom = -1;
        long idOption = -1;

        SymptomModel symptomModel = new SymptomModel(1, 1, 0, 0, 2, null, null, null, null, 0, 10, 10);
        SymptomDao symptomDao = new SymptomDaoImpl(appContext);
        try {
            idSymptom = symptomDao.insert(symptomModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SymptomCategoryOptionModel symptomCategoryOptionModel = new SymptomCategoryOptionModel(1, "option", 1);
        SymptomCategoryOptionDao symptomCategoryOptionDao = new SymptomCategoryOptionDaoImpl(appContext);
        try {
            idOption = symptomCategoryOptionDao.insert(symptomCategoryOptionModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

}