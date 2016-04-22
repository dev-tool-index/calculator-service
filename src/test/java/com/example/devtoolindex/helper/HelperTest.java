package com.example.devtoolindex.helper;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;

/**
 * Created by hongkailiu on 2016-04-16.
 */
public class HelperTest {

    private Helper unitUnderTest = new Helper();

    @Test public void testGetSystemEnv() throws Exception {
        unitUnderTest.getSystemEnv("PATH");
    }

    @Test public void testGetIPByHostname() throws Exception {
        String result = unitUnderTest.getIPByHostname("localhost");
        Assert.assertTrue(StringUtils.isNoneBlank(result));
    }

    @Test public void testGetIPByHostnameException() throws Exception {
        try {
            unitUnderTest.getIPByHostname("unknown-host");
            Assert.fail("UnknownHostException did not occur");
        } catch (Exception e){
            Assert.assertTrue(e instanceof UnknownHostException);
        }
    }

    @Test public void testIsValidIP() throws Exception {
        boolean result = unitUnderTest.isValidIP("127.0.0.1");
        Assert.assertEquals(true, result);
    }

    @Test public void testIsValidIPNull() throws Exception {
        boolean result = unitUnderTest.isValidIP(null);
        Assert.assertEquals(false, result);
    }

    @Test public void testIsValidIPInvalid() throws Exception {
        boolean result = unitUnderTest.isValidIP("invalid-ip");
        Assert.assertEquals(false, result);
    }
}
