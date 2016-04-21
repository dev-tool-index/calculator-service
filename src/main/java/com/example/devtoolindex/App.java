package com.example.devtoolindex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication @Slf4j public class App {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = null;
        try {
            applicationContext = SpringApplication.run(App.class, args);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (applicationContext != null) {
                applicationContext.close();
            }
        }
    }
}
