package com.example.devtoolindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringApplicationConfiguration(classes = {MockServletContext.class, TestContext.class})
@WebAppConfiguration public class CalculatorControllerMockMVCTest
    extends AbstractTestNGSpringContextTests {

    @Autowired private CalculatorController calculatorController;
    private MockMvc mvc;

    @BeforeMethod public void beforeMehtod() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(calculatorController).build();
    }

    @Test public void testAdd() throws Exception {
        // http://javaninja.net/2014/02/testing-spring-mvc-rest-controllers/
        mvc.perform(MockMvcRequestBuilders.get("/calc/add?arg1=3&arg2=2")
            .accept(MediaType.APPLICATION_JSON))
            //.andDo(print())
            .andExpect(status().isOk()).andExpect(jsonPath("$.result", is(5)));
    }
}
