package com.example.devtoolindex.javamelody;

import com.example.devtoolindex.App;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@SpringApplicationConfiguration(App.class)
@WebIntegrationTest("server.port:8090")
public class JavaMelodyIT extends
        AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(JavaMelodyIT.class);

    private static final String BASE_URL_MONITORING = "http://localhost:8090/monitoring";

    private RestTemplate template = new TestRestTemplate();

    @Test
    public void testGeneral() throws Exception {
        ResponseEntity<String> response = template.getForEntity(BASE_URL_MONITORING + "?format=json", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        String body = response.getBody();
        Assertions.assertThat(body).contains("application", "{", "}");
    }
}
