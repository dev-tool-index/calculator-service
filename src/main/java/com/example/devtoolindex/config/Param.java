package com.example.devtoolindex.config;

import com.example.devtoolindex.helper.Helper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.net.UnknownHostException;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@Slf4j public class Param {
    public static final String DATE_PATTERN = "yyyy.MM.dd G 'at' HH:mm:ss z";
    public static final String MONGO_IP_ENV_VAR_NAME = "MONGODB_PORT_27017_TCP_ADDR";
    public static final String MONGO_HOSTNAME = "mongodb";
    public static final String DEFAULT_MONGO_IP = "127.0.0.1";

    @Setter private static Helper helper = new Helper();

    public static String getMongoIP() {
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
        log.info("mongoIP is from DEFAULT_MONGO_IP");
        return DEFAULT_MONGO_IP;

    }
}
