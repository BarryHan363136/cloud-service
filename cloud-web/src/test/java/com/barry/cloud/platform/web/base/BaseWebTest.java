package com.barry.cloud.platform.web.base;

import com.barry.cloud.platform.web.starter.ApplicationStarter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@WebAppConfiguration
public class BaseWebTest {

    @Ignore
    @Test
    public void baseTest(){
    }

}