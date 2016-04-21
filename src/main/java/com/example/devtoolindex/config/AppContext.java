package com.example.devtoolindex.config;

import com.example.devtoolindex.interceptor.AccessInterceptor;
import com.example.devtoolindex.db.service.ArithmeticService;
import com.example.devtoolindex.db.service.ArithmeticServiceImpl;
import com.example.devtoolindex.db.service.IPStatService;
import com.example.devtoolindex.db.service.IPStatServiceImpl;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hongkailiu on 2016-04-13.
 */
@Slf4j @Configuration public class AppContext extends WebMvcConfigurerAdapter {

    @Bean public MongoDbFactory mongoDbFactory() throws Exception {
        String mongoIP = Param.getMongoIP();
        log.info("mongoIP: " + mongoIP);
        return new SimpleMongoDbFactory(new MongoClient(mongoIP), "testmongo");
    }

    @Bean public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean public IPStatService ipStatService() throws Exception {
        return new IPStatServiceImpl(mongoTemplate());
    }

    @Bean public ArithmeticService arithmeticService() {
        return new ArithmeticServiceImpl();
    }

    @Bean public AccessInterceptor accessInterceptor() {
        return new AccessInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor());
    }
}
