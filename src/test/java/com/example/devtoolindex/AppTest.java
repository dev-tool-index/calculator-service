package com.example.devtoolindex;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.support.GenericApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by hongkailiu on 2016-04-15.
 */
@PrepareForTest(SpringApplication.class) public class AppTest extends PowerMockTestCase {

    private String[] args = new String[] {};
    @Mock private SpringApplicationBuilder mockSpringApplicationBuilder;
    private App unitUnderTest = new App();

    @BeforeMethod public void beforeMethod(){

    }
    @Test public void testMain() throws Exception {
        PowerMockito.mockStatic(SpringApplication.class);
        Mockito.when(SpringApplication.run(App.class, args))
            .thenReturn(new GenericApplicationContext());

        App.main(args);

        PowerMockito.verifyStatic();
        SpringApplication.run(App.class, args);
    }

    @Test public void testConfigure() throws Exception {
        Mockito.when(mockSpringApplicationBuilder.sources(App.class)).thenReturn(null);
        unitUnderTest.configure(mockSpringApplicationBuilder);
        Mockito.verify(mockSpringApplicationBuilder, Mockito.times(1)).sources(App.class);
    }
}
