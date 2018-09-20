package com.barry.cloud.platform.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

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
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启注解
public class Oauth2Starter extends ResourceServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Starter.class, args);
    }

}
