package com.example.devtoolindex.helper;

import com.example.devtoolindex.config.Param;
import com.mongodb.MongoClientURI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

/**
 * Created by hongkailiu on 2016-07-09.
 */
@Component
@Slf4j
public class MongoDbFactoryHelper {

    @Autowired
    private Param param;
    @Autowired
    private Helper helper;

    public MongoDbFactory getMongoDbFactory(String defaultMongoIp) throws UnknownHostException {
        String uri = param.getMongoURI();
        if (StringUtils.isBlank(uri)) {
            String mongoIP = param.getMongoIP(defaultMongoIp);
            log.info("mongoIP: " + mongoIP);
            uri = "mongodb://" + mongoIP + ":" + "27017" + "/testmongo";
        }
        log.info("mongo uri: " + uri);
        return helper.getSimpleMongoDbFactory(new MongoClientURI(uri));

    }
}
