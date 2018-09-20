package com.barry.cloud.platform.serialnumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * TaskApplication [spring boot] 主方法
 *
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 *
 */
@SpringBootApplication
@ServletComponentScan
public class DistributedOrderNumberStarter {

    public static void main(String[] args) {
        SpringApplication.run(DistributedOrderNumberStarter.class, args);
    }

}
