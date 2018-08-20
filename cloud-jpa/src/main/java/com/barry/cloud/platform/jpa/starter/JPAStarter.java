package com.barry.cloud.platform.jpa.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * TaskApplication [spring boot] 主方法
 *
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 *
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.barry.cloud.platform.jpa"})
@EnableJpaRepositories(basePackages="com.barry.cloud.platform.jpa.dao")
@EntityScan(basePackages="com.barry.cloud.platform.jpa.entity")
@ServletComponentScan
public class JPAStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JPAStarter.class, args);
    }

}
