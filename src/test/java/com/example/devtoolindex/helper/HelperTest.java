package com.example.devtoolindex.helper;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

/**
 * Created by hongkailiu on 2016-04-16.
 */
public class HelperTest {

    private Helper unitUnderTest = new Helper();

    @Test
    public void testGetSystemEnv() throws Exception {
        unitUnderTest.getSystemEnv("PATH");
    }

    @Test
    public void testGetIPByHostname() throws Exception {
        String result = unitUnderTest.getIPByHostname("localhost");
        Assertions.assertThat(StringUtils.isNoneBlank(result)).isTrue();
    }

    @Test
    public void testGetIPByHostnameException() throws Exception {
        try {
            unitUnderTest.getIPByHostname("unknown-host");
            Assertions.fail("UnknownHostException did not occur");
        } catch (Exception e) {
            Assertions.assertThat(e instanceof UnknownHostException).isTrue();
        }
    }

    @Test
    public void testIsValidIP() throws Exception {
        boolean result = unitUnderTest.isValidIP("127.0.0.1");
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testIsValidIPNull() throws Exception {
        boolean result = unitUnderTest.isValidIP(null);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void testIsValidIPInvalid() throws Exception {
        boolean result = unitUnderTest.isValidIP("invalid-ip");
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void testLoadVersionFromResource() throws Exception {
        String suffix = "abc";
        String path = Helper.VERSION + "-" + suffix;
        String result = unitUnderTest.loadVersionFromResource(path);
        Assertions.assertThat(result).isEqualTo(suffix);
    }

    @Test
    public void testLoadVersionFromResourceNoFileFound() throws Exception {
        String suffix = "no-file-found";
        String path = Helper.VERSION + "-" + suffix;
        String result = unitUnderTest.loadVersionFromResource(path);
        Assertions.assertThat(result).isEqualTo(Helper.NO_VERSION_FILE_FOUND);
    }

    @Test
    public void testLoadVersionFromResourceEmptyFile() throws Exception {
        String suffix = "empty";
        String path = Helper.VERSION + "-" + suffix;
        String result = unitUnderTest.loadVersionFromResource(path);
        Assertions.assertThat(result).isEqualTo(Helper.EMPTY_VERSION_FILE);
    }
}
