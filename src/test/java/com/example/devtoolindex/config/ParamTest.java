package com.example.devtoolindex.config;

import com.example.devtoolindex.helper.SystemHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by hongkailiu on 2016-04-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParamTest {
    private Param p;
    @Mock private SystemHelper mockHelper;

    @Before public void setUp() throws Exception {
        Param.setHelper(mockHelper);
    }

    @Test public void test() {
        p = new Param();
        Assert.assertNotNull(p);
    }

    @Test public void testGetMongoIPWithNull() {
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(null);
        String result = Param.getMongoIP();
        Assert.assertEquals(Param.DEFAULT_MONGO_IP, result);
    }

    @Test public void testGetMongoIPWithInvalidIP() {
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn("invalid ip");
        String result = Param.getMongoIP();
        Assert.assertEquals(Param.DEFAULT_MONGO_IP, result);
    }

    @Test public void testGetMongoIPWithValidIP() {
        String validIP = "8.8.8.8";
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(validIP);
        String result = Param.getMongoIP();
        Assert.assertEquals(validIP, result);
    }
}
