package com.example.devtoolindex.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomDateDeserializerTest {

    private CustomDateDeserializer unitUnderTest;
    @Mock private JsonParser mockParser;
    @Mock private DeserializationContext mockContext;
    @Mock private ObjectCodec mockObjectCodec;
    @Mock private JsonNode mockNode;

    @Before public void setUp() throws Exception {
        unitUnderTest = new CustomDateDeserializer();
        Mockito.when(mockParser.getCodec()).thenReturn(mockObjectCodec);
        Mockito.when(mockObjectCodec.readTree(mockParser)).thenReturn(mockNode);
    }

    @After public void tearDown() throws Exception {

    }

    @Test public void testDeserializeWithEmptyString() throws Exception {
        Mockito.when(mockNode.asText()).thenReturn("");
        Date result = unitUnderTest.deserialize(mockParser, mockContext);
        Assert.assertNull(result);
    }

    @Test public void testDeserializeWithInvalidString() throws Exception {
        Mockito.when(mockNode.asText()).thenReturn("invalid string");
        Date result = unitUnderTest.deserialize(mockParser, mockContext);
        Assert.assertNull(result);
    }
}
