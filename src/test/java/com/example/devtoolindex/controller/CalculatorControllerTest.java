package com.example.devtoolindex.controller;

import com.example.devtoolindex.db.service.ArithmeticServiceImpl;
import com.example.devtoolindex.response.CalcResult;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorControllerTest {

    private CalculatorController controller;

    @BeforeMethod public void beforeMehtod() throws Exception {
        controller = new CalculatorController();
        Assertions.assertThat(controller).isNotNull();
        controller.setArithmeticService(new ArithmeticServiceImpl());
    }

    @Test public void testAdd() throws Exception {

        CalcResult result = controller.add(2, 3);
        Assertions.assertThat(result.getResult()).isEqualTo(5);

    }
}
