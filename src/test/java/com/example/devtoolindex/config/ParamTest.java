package com.example.devtoolindex.config;

import com.example.devtoolindex.helper.Helper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownHostException;


/**
 * Created by hongkailiu on 2016-04-16.
 */
@RunWith(MockitoJUnitRunner.class) public class ParamTest {
    private Param p;
    @Mock private Helper mockHelper;
    private static final String VALID_IP = "valid.ip";

    @Before public void setUp() throws Exception {
        Param.setHelper(mockHelper);
    }

    @Test public void testConstructor() {
        p = new Param();
        Assert.assertNotNull(p);
    }

    @Test public void testGetMongoIPWithInvalidInvalid() throws Exception {
        Mockito.when(mockHelper.isValidIP(null)).thenReturn(false).thenReturn(false);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(null);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(null);
        String result = Param.getMongoIP();
        Assert.assertEquals(Param.DEFAULT_MONGO_IP, result);
        Mockito.verify(mockHelper, Mockito.times(2)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(1)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithInvalidValid() throws Exception {
        Mockito.when(mockHelper.isValidIP(null)).thenReturn(false);
        Mockito.when(mockHelper.isValidIP(VALID_IP)).thenReturn(true);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(null);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(VALID_IP);
        String result = Param.getMongoIP();
        Assert.assertEquals(VALID_IP, result);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(1)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithValidInvalid() throws Exception {
        Mockito.when(mockHelper.isValidIP(VALID_IP)).thenReturn(true);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(VALID_IP);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(null);
        String result = Param.getMongoIP();
        Assert.assertEquals(VALID_IP, result);
        Mockito.verify(mockHelper, Mockito.times(0)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(0)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithValidValid() throws Exception {

        Mockito.when(mockHelper.isValidIP(VALID_IP)).thenReturn(true);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(VALID_IP);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(null);
        String result = Param.getMongoIP();
        Assert.assertEquals(VALID_IP, result);
        Mockito.verify(mockHelper, Mockito.times(0)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(0)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithInvalidException() throws Exception {
        Mockito.when(mockHelper.isValidIP(null)).thenReturn(false).thenReturn(false);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(null);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenThrow(new UnknownHostException("test"));
        String result = Param.getMongoIP();
        Assert.assertEquals(Param.DEFAULT_MONGO_IP, result);
        Mockito.verify(mockHelper, Mockito.times(2)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(1)).getIPByHostname(Matchers.anyString());
    }
}
