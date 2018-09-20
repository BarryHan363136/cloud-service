package com.barry.cloud.platform.oap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * TaskApplication [spring boot] 主方法
 *
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 *
 */
@SpringBootApplication
@MapperScan("com.barry.cloud.platform.oap.mapper")
@ServletComponentScan
public class OAPStarter {

    public static void main(String[] args) {
        SpringApplication.run(OAPStarter.class, args);
    }

    /**
     * 返回http status = 304
     * 页面未方面变化则返回304,不再从数据库获取数据
     * ShallowEtagHeaderFilter每次过滤请求时通过hash算法判断网页内容是否发生变化
     * */
//    @Bean
//    public ShallowEtagHeaderFilter shallowEtagHeaderFilter(){
//        return new ShallowEtagHeaderFilter();
//    }

}
