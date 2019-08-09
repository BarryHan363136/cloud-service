package com.barry.cloud.platform.simpleimage.base;

import com.barry.cloud.platform.simpleimage.SimpleImageStarter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleImageStarter.class)
@WebAppConfiguration
public class BaseWebTest {

    @Ignore
    @Test
    public void baseTest(){
    }

}