package com.example.devtoolindex.controller;

import com.example.devtoolindex.response.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class) @SpringApplicationConfiguration(CalculatorController.class)
public class CalculatorControllerIT {

    private static final String BASE_URL_ADD = "http://127.0.0.1:8080/calc/add";

    private RestTemplate template = new TestRestTemplate();

    @Before public void setUp() throws Exception {
    }

    @After public void tearDown() throws Exception {

    }

    @Test public void testAddDefault() throws Exception {


        ResponseEntity<Result> response = template.getForEntity(BASE_URL_ADD, Result.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Result result = response.getBody();
        Assert.assertEquals(0, result.getResult());

    }

    @Test public void testAdd() throws Exception {

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("arg1", "2");
        urlVariables.put("arg2", "3");

        ResponseEntity<Result> response =
            template.getForEntity(BASE_URL_ADD + "?arg1={arg1}&arg2={arg2}", Result.class, 2, 3);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Result result = response.getBody();
        Assert.assertEquals(5, result.getResult());

    }
}
