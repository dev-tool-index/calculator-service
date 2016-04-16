package com.example.devtoolindex.config;

import com.example.devtoolindex.helper.SystemHelper;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * Created by hongkailiu on 2016-04-16.
 */
public class Param {
    public static final String DATE_PATTERN = "yyyy.MM.dd G 'at' HH:mm:ss z";
    public static final String MONGO_IP_ENV_VAR_NAME = "MONGODB_PORT_27017_TCP_ADDR";
    public static final String DEFAULT_MONGO_IP = "127.0.0.1";

    @Setter private static SystemHelper helper = new SystemHelper();

    public static String getMongoIP() {
        String mongoIP = helper.getSystemEnv(MONGO_IP_ENV_VAR_NAME);
        if (StringUtils.isNoneBlank(mongoIP) && InetAddressValidator.getInstance()
            .isValid(mongoIP)) {
            return mongoIP;
        }
        return DEFAULT_MONGO_IP;
    }
}
