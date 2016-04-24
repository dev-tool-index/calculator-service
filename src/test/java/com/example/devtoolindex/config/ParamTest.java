package com.example.devtoolindex.config;

import com.example.devtoolindex.helper.Helper;
import org.assertj.core.api.Assertions;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.UnknownHostException;


/**
 * Created by hongkailiu on 2016-04-16.
 */
public class ParamTest {
    @Mock private Helper mockHelper;
    private static final String VALID_IP = "valid.ip";
    private static final String DEFAULT_MONGO_IP = "127.0.0.1";
    @InjectMocks private Param unitUnderTest;

    @BeforeMethod public void beforeMethod() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitUnderTest.setHelper(mockHelper);
    }

    @Test public void testConstructor() {
        unitUnderTest = new Param();
        Assertions.assertThat(unitUnderTest).isNotNull();
    }

    @Test public void testGetMongoIPWithInvalidInvalid() throws Exception {
        Mockito.when(mockHelper.isValidIP(null)).thenReturn(false).thenReturn(false);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(null);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(null);
        String result = unitUnderTest.getMongoIP(DEFAULT_MONGO_IP);
        Assertions.assertThat(result).isEqualTo(DEFAULT_MONGO_IP);
        Mockito.verify(mockHelper, Mockito.times(2)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(1)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithInvalidValid() throws Exception {
        Mockito.when(mockHelper.isValidIP(null)).thenReturn(false);
        Mockito.when(mockHelper.isValidIP(VALID_IP)).thenReturn(true);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(null);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(VALID_IP);
        String result = unitUnderTest.getMongoIP(DEFAULT_MONGO_IP);
        Assertions.assertThat(result).isEqualTo(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(1)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithValidInvalid() throws Exception {
        Mockito.when(mockHelper.isValidIP(VALID_IP)).thenReturn(true);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(VALID_IP);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(null);
        String result = unitUnderTest.getMongoIP(DEFAULT_MONGO_IP);
        Assertions.assertThat(result).isEqualTo(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(0)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(0)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithValidValid() throws Exception {

        Mockito.when(mockHelper.isValidIP(VALID_IP)).thenReturn(true);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(VALID_IP);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenReturn(null);
        String result = unitUnderTest.getMongoIP(DEFAULT_MONGO_IP);
        Assertions.assertThat(result).isEqualTo(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(0)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).isValidIP(VALID_IP);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(0)).getIPByHostname(Matchers.anyString());
    }

    @Test public void testGetMongoIPWithInvalidException() throws Exception {
        Mockito.when(mockHelper.isValidIP(null)).thenReturn(false).thenReturn(false);
        Mockito.when(mockHelper.getSystemEnv(Matchers.anyString())).thenReturn(null);
        Mockito.when(mockHelper.getIPByHostname(Matchers.anyString())).thenThrow(new UnknownHostException("test"));
        String result = unitUnderTest.getMongoIP(DEFAULT_MONGO_IP);
        Assertions.assertThat(result).isEqualTo(DEFAULT_MONGO_IP);
        Mockito.verify(mockHelper, Mockito.times(2)).isValidIP(null);
        Mockito.verify(mockHelper, Mockito.times(1)).getSystemEnv(Matchers.anyString());
        Mockito.verify(mockHelper, Mockito.times(1)).getIPByHostname(Matchers.anyString());
    }
}
