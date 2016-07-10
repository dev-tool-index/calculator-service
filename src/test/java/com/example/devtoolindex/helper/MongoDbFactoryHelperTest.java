package com.example.devtoolindex.helper;

import com.example.devtoolindex.config.Param;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by hongkailiu on 2016-07-09.
 */
public class MongoDbFactoryHelperTest {

    @InjectMocks private MongoDbFactoryHelper unitUnderTest;
    @Mock private Helper mockHelper;
    @Mock private Param mockParam;
    @Mock private MongoDbFactory mockMongoDbFactory;
    private static final String FAKE_URI = "mongodb://fake-uri";

    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMongoDbFactory() throws Exception {

        Assertions.assertThat(unitUnderTest).isNotNull();

        Mockito.when(mockParam.getMongoURI()).thenReturn(FAKE_URI);
        Mockito.when(mockHelper.getSimpleMongoDbFactory(Mockito.any())).thenReturn(mockMongoDbFactory);

        MongoDbFactory result = unitUnderTest.getMongoDbFactory(null);
        Assertions.assertThat(result).isEqualTo(mockMongoDbFactory);
    }

}