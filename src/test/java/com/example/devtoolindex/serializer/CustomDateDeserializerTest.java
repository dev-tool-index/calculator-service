package com.example.devtoolindex.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by hongkailiu on 2016-04-16.
 */
public class CustomDateDeserializerTest {

    @InjectMocks private CustomDateDeserializer unitUnderTest;
    @Mock private JsonParser mockParser;
    @Mock private DeserializationContext mockContext;
    @Mock private ObjectCodec mockObjectCodec;
    @Mock private JsonNode mockNode;

    @BeforeMethod public void beforeMethod() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockParser.getCodec()).thenReturn(mockObjectCodec);
        Mockito.when(mockObjectCodec.readTree(mockParser)).thenReturn(mockNode);
    }

    @AfterMethod public void afterMethod() throws Exception {

    }

    @Test public void testDeserializeWithEmptyString() throws Exception {
        Mockito.when(mockNode.asText()).thenReturn("");
        Date result = unitUnderTest.deserialize(mockParser, mockContext);
        Assertions.assertThat(result).isNull();
    }

    @Test public void testDeserializeWithInvalidString() throws Exception {
        Mockito.when(mockNode.asText()).thenReturn("invalid string");
        Date result = unitUnderTest.deserialize(mockParser, mockContext);
        Assertions.assertThat(result).isNull();
    }
}
