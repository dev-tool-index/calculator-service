package com.example.devtoolindex.helper;

import org.junit.Test;

/**
 * Created by hongkailiu on 2016-04-16.
 */
public class SystemHelperTest {

    private SystemHelper unitUnderTest = new SystemHelper();

    @Test public void testGetSystemEnv() throws Exception {
        unitUnderTest.getSystemEnv("PATH");
    }
}
