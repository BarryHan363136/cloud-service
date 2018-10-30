package com.barry.cloud.platform.routing.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description: 切入点和切面
 * @Author Tongshan.Han@partner.bmw.com
 * @Date 2018/10/30 17:46
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    @Before("execution(* com.barry.cloud.platform.routing.mapper.*.select*(..)) || " +
            "execution(* com.barry.cloud.platform.routing.mapper.*.count*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        log.info("dataSource切换到：Read");
    }

    @Before("execution(* com.barry.cloud.platform.routing.mapper.*.insert*(..)) || " +
            "execution(* com.barry.cloud.platform.routing.mapper.*.update*(..)) || " +
            "execution(* com.barry.cloud.platform.routing.mapper.*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        log.info("dataSource切换到：write");
    }
}