package com.example.devtoolindex.controller;

import com.example.devtoolindex.App;
import com.example.devtoolindex.response.CalcResult;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@SpringApplicationConfiguration(App.class)
@WebIntegrationTest("server.port:8090")
public class CalculatorControllerIT extends AbstractTestNGSpringContextTests {

    private static final String BASE_URL_ADD = "http://localhost:8090/calc/add";

    private RestTemplate template = new TestRestTemplate();

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
