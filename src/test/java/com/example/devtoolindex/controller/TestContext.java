package com.example.devtoolindex.controller;

import com.example.devtoolindex.db.service.ArithmeticService;
import com.example.devtoolindex.db.service.ArithmeticServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hongkailiu on 2016-04-23.
 */
@Configuration public class TestContext {
    @Bean public ArithmeticService arithmeticService() {
        return new ArithmeticServiceImpl();
    }

    @Bean public CalculatorController calculatorController() {
        return new CalculatorController();
    }
}
