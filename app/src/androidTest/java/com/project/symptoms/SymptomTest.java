package com.project.symptoms;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.project.symptoms.db.dao.SymptomDao;
import com.project.symptoms.db.dao.SymptomDaoImpl;
import com.project.symptoms.db.model.SymptomModel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SymptomTest {
    Context appContext;
    SymptomDao symptomDao;

    @Before
    public void setUp(){
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        symptomDao = new SymptomDaoImpl(appContext);
    }

    @Test
    public void givenSymptom_whenInsert_thenReturnGeneratedId() {
        //given
        long generatedId = -1;
        SymptomModel newSymptom = new SymptomModel(0, 0, 0, 0, 0, "description", "intensity", "causingDrug", "causingFood", 0, 0,1);

        //when
        try {
            generatedId = symptomDao.insert(newSymptom);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(generatedId).isNotEqualTo(-1);
    }

    @Test
    public void givenExistedSymptoms_whenListAll_thenReturnedList() {
        //given
        List<SymptomModel> symptomModelList = null;
        SymptomModel symptom1 = new SymptomModel(0, 0, 0, 0, 0, "description", "intensity", "causingDrug", "causingFood", 0, 0,1);
        SymptomModel symptom2 = new SymptomModel(10, 10, 0, 0, 0, "description", "intensity", "causingDrug", "causingFood", 0, 0,1);

        try {
            symptomDao.insert(symptom1);
            symptomDao.insert(symptom2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //when
        try {
            symptomModelList = symptomDao.listAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //then
        assertThat(symptomModelList.size()).isEqualTo(2);
    }

    @Test
    public void givenExistedSymptoms_whenListAllByDateAndSide_thenReturnedList() {
        //given
        List<SymptomModel> symptomModelList = null;

        SymptomModel symptom1 = new SymptomModel(0, 0, 0, 0, 0, "description", "intensity", "causingDrug", "causingFood", 0, 0,1);
        SymptomModel symptom2 = new SymptomModel(10, 10, 0, 0, 0, "description", "intensity", "causingDrug", "causingFood", 0, 0,0);
        try {
            symptomDao.insert(symptom1);
            symptomDao.insert(symptom2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //when
        try {
            symptomModelList = symptomDao.listAll(0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        assertThat(symptomModelList.size()).isEqualTo(1);
        assertThat(symptomModelList.get(0).getCirclePosX()).isEqualTo(0);
    }
}
