package com.example.devtoolindex.helper;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import com.mongodb.MongoClientURI;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@Component
@Slf4j
public class Helper {

    private String appVersion;

    /* package */ static final String EMPTY_VERSION_FILE = "empty version file";
    /* package */ static final String NO_VERSION_FILE_FOUND = "no version file found";
    /* package */ static final String VERSION = "VERSION";

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

    public String getAppVersion() {
        if (appVersion == null) {
            synchronized (this) {
                if (appVersion == null) {
                    appVersion = loadVersionFromResource();
                }
            }
        }
        return appVersion;
    }

    private String loadVersionFromResource() {
        return loadVersionFromResource(VERSION);
    }

    /* package */ String loadVersionFromResource(String path) {
        try {
            String version = StringUtils
                .trim(Resources.toString(Resources.getResource(VERSION),
                    Charsets.UTF_8));
            if (StringUtils.isNoneBlank(version)) {
                return version;
            }
            log.error(EMPTY_VERSION_FILE);
            return EMPTY_VERSION_FILE;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return NO_VERSION_FILE_FOUND;
        }
    }
}
