package com.barry.cloud.platform.security.jwt.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * TaskApplication [spring boot] 主方法
 *
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 *
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.barry.cloud.platform.security.jwt"})
@ServletComponentScan
public class SecurityJWTStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJWTStarter.class, args);
    }

}
