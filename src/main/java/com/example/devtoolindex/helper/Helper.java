package com.example.devtoolindex.helper;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@Component @Slf4j public class Helper {
    public String getSystemEnv(String name) {
        return System.getenv(name);
    }

    public String getIPByHostname(String hostname) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(hostname);
        return address.getHostAddress();
    }

    public boolean isValidIP(String ip) {
        return StringUtils.isNoneBlank(ip) && InetAddressValidator.getInstance().isValid(ip);
    }

    public MongoDbFactory getSimpleMongoDbFactory(MongoClientURI uri) throws UnknownHostException {
        return new SimpleMongoDbFactory(uri);
    }

    public MongoDbFactory getSimpleMongoDbFactory(MongoClient mongoClient, String databaseName) throws UnknownHostException {
        return new SimpleMongoDbFactory(mongoClient, databaseName);
    }
}
