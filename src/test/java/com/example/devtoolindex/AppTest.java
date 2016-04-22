package com.example.devtoolindex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by hongkailiu on 2016-04-15.
 */
@RunWith(PowerMockRunner.class) @PrepareForTest(SpringApplication.class) public class AppTest {

    private String[] args = new String[] {};
    @Before public void setup(){
        PowerMockito.mockStatic(SpringApplication.class);
    }
    @Test public void testMain() throws Exception {
        Mockito.when(SpringApplication.run(App.class, args))
            .thenReturn(new GenericApplicationContext());

        //https://github.com/jayway/powermock/wiki/MockitoUsage
        //verify does not work
        //however, jacoco shows that it is covered and without
        //this test it is not.
        //PowerMockito.verifyStatic();
        App.main(args);

    }
}
