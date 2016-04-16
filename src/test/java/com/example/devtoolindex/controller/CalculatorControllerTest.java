package com.example.devtoolindex.controller;

import com.example.devtoolindex.db.service.ArithmeticServiceImpl;
import com.example.devtoolindex.response.CalcResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorControllerTest {

    private CalculatorController controller;

    @Before public void setUp() throws Exception {
        controller = new CalculatorController();
        Assert.assertNotNull(controller);
        controller.setArithmeticService(new ArithmeticServiceImpl());
    }



    @After public void tearDown() throws Exception {

    }

    @Test public void testAdd() throws Exception {

        CalcResult result = controller.add(2, 3);
        Assert.assertEquals(5, result.getResult());

    }
}
