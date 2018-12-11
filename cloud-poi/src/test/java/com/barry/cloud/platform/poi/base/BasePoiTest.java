package com.barry.cloud.platform.poi.base;

import com.barry.cloud.platform.poi.PoiServiceStarter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PoiServiceStarter.class)
@WebAppConfiguration
public class BasePoiTest {

    @Ignore
    @Test
    public void baseTest(){
    }

}