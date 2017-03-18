package com.example.devtoolindex.controller;

import com.example.devtoolindex.response.StatResult;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StatControllerIT extends
    AbstractTestNGSpringContextTests {

  private static final Logger logger = LoggerFactory.getLogger(StatControllerIT.class);

  private static final String BASE_URL_ADD = "/stat/general";

  @Autowired
  private TestRestTemplate template;

  @Test
  public void testGeneral() throws Exception {
    ResponseEntity<StatResult> response = template.getForEntity(BASE_URL_ADD, StatResult.class);
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    StatResult result = response.getBody();
    int count0 = result.getCount();
    Assertions.assertThat(count0).isGreaterThanOrEqualTo(0);

    Thread.sleep(TimeUnit.SECONDS.toMillis(1));

    response = template.getForEntity(BASE_URL_ADD, StatResult.class);
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    result = response.getBody();
    int count1 = result.getCount();
    logger.debug("count0: " + count0);
    logger.debug("count1: " + count1);
    Assertions.assertThat(count1 - count0).isEqualTo(1);

  }
}
