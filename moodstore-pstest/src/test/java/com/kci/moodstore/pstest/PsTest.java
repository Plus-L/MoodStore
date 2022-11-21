package com.kci.moodstore.pstest;

import com.kci.moodstore.pstest.service.PsychometricTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @program: moodstore
 * @description:
 * @author: PlusL
 * @create: 2022-10-23 14:51
 **/
@SpringBootTest
@ContextConfiguration(classes = PstestApplication.class)
public class PsTest {

    @Autowired
    private PsychometricTestService service;

    @Test
    public void ServiceTest() {
    }

}
