package com.barry.cloud.platform.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

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
public class Oauth2Starter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Starter.class, args);
    }

}
