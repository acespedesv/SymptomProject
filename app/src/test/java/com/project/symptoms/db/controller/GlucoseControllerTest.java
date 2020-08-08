package com.project.symptoms.db.controller;

import com.project.symptoms.db.dao.GlucoseDaoImpl;
import com.project.symptoms.db.model.GlucoseModel;
import com.project.symptoms.util.DateTimeUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GlucoseControllerTest{

    GlucoseDaoImpl mockDao;
    GlucoseController controller;

    @Before
    public void setup(){
        mockDao = Mockito.mock(GlucoseDaoImpl.class);
        controller = GlucoseController.getInstance(null);
        controller.setGlucoseDao(mockDao);
    }

    @Test
    public void GivenSomeRecords_WhenSelectAll_ThenReturnsAList() throws Exception{
        // Given
        List<GlucoseModel> fixedList = new ArrayList<GlucoseModel>(){{
            add(new GlucoseModel(10,20,30));
        }};
        when(mockDao.listAll()).thenReturn(fixedList);

        // When
        List<GlucoseModel> returnedList = controller.listAll();

        // Then
        assertNotNull(returnedList);
        assertEquals(returnedList.size(), 1);
    }

    @Test // Not sure about this test
    public void GivenARecord_WhenUpdateThen_ReturnsOne() throws Exception{
        // Given
        long Id = 1;
        when(mockDao.update(new GlucoseModel(Id,2,3,4))).thenReturn(1);
        controller.setDateTimeUtils(createMockedDateTimeUtils());

        // When
        int result = controller.update(Id, 60, "jue. 02 jul. 2020","06:00 a.m.");

        // Then
        assertEquals(1, result);
    }

    @Test // Not sure about this test
    public void GivenSomeDataWhen_Insert_ThenReturnsAnId() throws Exception{
        // Given
        controller.setDateTimeUtils(createMockedDateTimeUtils());

        // When
        long result = controller.insert(50, "asdasd","asd");

        // Then
        assertNotEquals(-1, result);
    }

    @Test
    public void GivenOneRecord_WhenSelect_ThenReturnsAModel() throws  Exception{
        // Given
        GlucoseModel model = new GlucoseModel(1,2,3,4);
        when(mockDao.select(1)).thenReturn(model);

        // When
        GlucoseModel result = controller.select(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(2, result.getValue());
        assertEquals(3, result.getDate());
        assertEquals(4, result.getTime());

    }

    @Test
    public void GivenOneRecord_WhenDelete_ThenReturnsOne() throws Exception{
        // Given
        GlucoseModel model = new GlucoseModel(1,2,3,4);
        when(mockDao.delete(1)).thenReturn(1);

        // When
        int result = controller.delete(1);

        // Then
        assertEquals(1, result);
    }


    private DateTimeUtils createMockedDateTimeUtils() {
        DateTimeUtils mockUtils = Mockito.mock(DateTimeUtils.class);
        try {
            mockUtils = Mockito.mock(DateTimeUtils.class);
            when(mockUtils.getDateFromString(any())).thenReturn(new Date());
            when(mockUtils.getTimeFromString(any())).thenReturn(new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
        return mockUtils;
    }

}