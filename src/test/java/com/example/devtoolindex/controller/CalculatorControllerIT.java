package com.example.devtoolindex.controller;

import com.example.devtoolindex.response.CalcResult;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class CalculatorControllerIT extends AbstractTestNGSpringContextTests {

    private static final String BASE_URL_ADD = "/calc/add";

    @Autowired
    private TestRestTemplate template;

    @BeforeMethod public void beforeMethod() throws Exception {
    }

    @Test public void testAddDefault() throws Exception {


        ResponseEntity<CalcResult> response = template.getForEntity(BASE_URL_ADD, CalcResult.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        CalcResult result = response.getBody();
        Assertions.assertThat(result.getResult()).isEqualTo(0);

    }

    @Test public void testAdd() throws Exception {

        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("arg1", "2");
        urlVariables.put("arg2", "3");

        ResponseEntity<CalcResult> response =
            template.getForEntity(BASE_URL_ADD + "?arg1={arg1}&arg2={arg2}", CalcResult.class, 2, 3);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        CalcResult result = response.getBody();
        Assertions.assertThat(result.getResult()).isEqualTo(5);

    }
}
