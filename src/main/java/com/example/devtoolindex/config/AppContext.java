package com.example.devtoolindex.config;

import com.example.devtoolindex.helper.Helper;
import com.example.devtoolindex.helper.MongoDbFactoryHelper;
import com.example.devtoolindex.interceptor.AccessInterceptor;
import com.example.devtoolindex.db.service.ArithmeticService;
import com.example.devtoolindex.db.service.ArithmeticServiceImpl;
import com.example.devtoolindex.db.service.IPStatService;
import com.example.devtoolindex.db.service.IPStatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.UnknownHostException;
import java.util.LinkedHashMap;

/**
 * Created by hongkailiu on 2016-04-13.
 */
@Slf4j @Configuration public class AppContext extends WebMvcConfigurerAdapter {

    @Autowired private MongoDbFactoryHelper mongoDbFactoryHelper;

    @Autowired private Helper helper;

    @Value("${default.mongo.ip}")
    private String defaultMongoIp;

    @Value("${deployed.by}")
    private String deployedBy;

    @Bean public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return mongoDbFactoryHelper.getMongoDbFactory(defaultMongoIp);
    }

    @Bean public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean public IPStatService ipStatService() throws UnknownHostException {
        return new IPStatServiceImpl(mongoTemplate());
    }

    @Bean public ArithmeticService arithmeticService() {
        return new ArithmeticServiceImpl();
    }

    @Bean public AccessInterceptor accessInterceptor() {
        return new AccessInterceptor();
    }

    @Bean
    public InfoEndpoint infoEndpoint() {
        LinkedHashMap<String, Object> map = new LinkedHashMap();
        map.put("version", helper.getAppVersion());
        map.put("deployedBy", deployedBy);
        return new InfoEndpoint(map);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/health");
    }
}
