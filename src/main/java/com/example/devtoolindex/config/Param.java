package com.example.devtoolindex.config;

import com.example.devtoolindex.helper.Helper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@Component @Slf4j public class Param {
    public static final String DATE_PATTERN = "yyyy.MM.dd G 'at' HH:mm:ss z";
    public static final String MONGO_IP_ENV_VAR_NAME = "MONGODB_PORT_27017_TCP_ADDR";
    public static final String MONGO_URI_ENV_VAR_NAME = "MONGODB_URI";
    public static final String MONGO_HOSTNAME = "mongodb";

    @Autowired private Helper helper;

    public String getMongoIP(String defaultMongoIP) {
        String mongoIP = helper.getSystemEnv(MONGO_IP_ENV_VAR_NAME);
        if (helper.isValidIP(mongoIP)) {
            log.info("mongoIP is from MONGODB_PORT_27017_TCP_ADDR");
            return mongoIP;
        }
        try {
            mongoIP = helper.getIPByHostname(MONGO_HOSTNAME);
        } catch (UnknownHostException e) {
            log.warn(e.getMessage());
        }
        if (helper.isValidIP(mongoIP)) {
            log.info("mongoIP is from mongodb");
            return mongoIP;
        }
        log.info("mongoIP is from defaultMongoIP");
        return defaultMongoIP;

    }

    public String getMongoURI() {
        return helper.getSystemEnv(MONGO_URI_ENV_VAR_NAME);
    }
}
