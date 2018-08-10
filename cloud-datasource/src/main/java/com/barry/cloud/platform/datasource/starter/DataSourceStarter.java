package com.barry.cloud.platform.datasource.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
@ComponentScan(basePackages={"com.barry.cloud.platform.datasource"})
@MapperScan("com.barry.cloud.platform.datasource.mapper")
@ServletComponentScan
public class DataSourceStarter {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceStarter.class, args);
    }

}
