package com.example.devtoolindex.controller;

import com.example.devtoolindex.App;
import com.example.devtoolindex.response.StatResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@RunWith(SpringJUnit4ClassRunner.class) @SpringApplicationConfiguration(App.class)
@WebIntegrationTest("server.port:8090") public class StatControllerIT {

    private static final Logger logger = LoggerFactory.getLogger(StatControllerIT.class);

    private static final String BASE_URL_ADD = "http://localhost:8090/stat/general";

    private RestTemplate template = new TestRestTemplate();

    @Before public void setUp() throws Exception {
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void testGeneral() throws Exception {
        ResponseEntity<StatResult> response = template.getForEntity(BASE_URL_ADD, StatResult.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        StatResult result = response.getBody();
        int count0 = result.getCount();
        Assert.assertTrue(0 <= count0);

        Thread.sleep(TimeUnit.SECONDS.toMillis(1));

        response = template.getForEntity(BASE_URL_ADD, StatResult.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        result = response.getBody();
        int count1 = result.getCount();
        logger.debug("count0: " + count0);
        logger.debug("count1: " + count1);
        Assert.assertTrue(1 == count1 - count0);

    }
}
