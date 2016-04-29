package com.example.devtoolindex.controller;

import com.example.devtoolindex.db.service.IPStatService;
import com.example.devtoolindex.response.StatResult;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class StatControllerTest {

    @Mock IPStatService mockIPStatService;
    @InjectMocks private StatController controller;

    @BeforeClass public void beforeClass() {
        MockitoAnnotations.initMocks(this);
    }

    @Test public void testGeneral() throws Exception {
        Mockito.when(mockIPStatService.findLatest()).thenReturn(null);

        StatResult result = controller.general();
        Assertions.assertThat(result.getCount()).isEqualTo(0);
        Assertions.assertThat(result.getLastVisit()).isNull();
    }
}
