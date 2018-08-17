package com.barry.cloud.platform.shiro.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(basePackages={"com.barry.cloud.platform.shiro"})
@MapperScan("com.barry.cloud.platform.shiro.mapper")
@EnableTransactionManagement
@ServletComponentScan
public class ShiroStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ShiroStarter.class, args);
    }

}
