package com.example.devtoolindex.contributor;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by hongkailiu on 2017-03-17.
 */
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class MyInfoContributorIT extends AbstractTestNGSpringContextTests {
  private static final String BASE_URL_MONITORING = "/info";

  @Autowired
  private TestRestTemplate template;

  @Test
  public void testInfo() throws Exception {
    for (int i=0;i<2;i++){
      ResponseEntity<String> response = template.getForEntity(BASE_URL_MONITORING, String.class);
      Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
  }
}