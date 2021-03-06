package com.example.devtoolindex.javamelody;

import org.apache.tomcat.util.codec.binary.Base64;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class JavaMelodyIT extends
        AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(JavaMelodyIT.class);

    private static final String BASE_URL_MONITORING = "/monitoring";

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testMonitoringWithoutAuth() throws Exception {
        ResponseEntity<String> response = template.getForEntity(BASE_URL_MONITORING + "?format=json", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testMonitoringWithAuth() throws Exception {

        String plainCreds = "admin:dev.tool.index";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);


        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(BASE_URL_MONITORING + "?format=json", HttpMethod.GET, request, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        String body = response.getBody();
        Assertions.assertThat(body).contains("application", "{", "}");
    }

    @Test
    public void testMonitoringWithWrongPassword() throws Exception {

        String plainCreds = "admin:password";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);


        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(BASE_URL_MONITORING + "?format=json", HttpMethod.GET, request, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

    }
}
