package com.barry.cloud.platform.security;

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
public class SecurityStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecurityStarter.class, args);
    }

}
