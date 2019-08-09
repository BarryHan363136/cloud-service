package com.barry.cloud.platform.simpleimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TaskApplication [spring boot] 主方法
 *
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
public class SimpleImageStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SimpleImageStarter.class, args);
    }

}
