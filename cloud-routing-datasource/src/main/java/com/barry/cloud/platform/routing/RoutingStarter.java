package com.barry.cloud.platform.routing;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.barry.cloud.platform.routing.mapper")
@ServletComponentScan
public class RoutingStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RoutingStarter.class, args);
    }

}
