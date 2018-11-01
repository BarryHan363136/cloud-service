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

    @Before("execution(* com.barry.cloud.platform.routing.service..*.select*(..)) " +
            "|| execution(* com.barry.cloud.platform.routing.service..*.get*(..)) " +
            "|| execution(* com.barry.cloud.platform.routing.service..*.find*(..)) " +
            "|| execution(* com.barry.cloud.platform.routing.service..*.query*(..))")

    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        log.info("dataSource切换到：Read");
    }

    @Before("execution(* com.barry.cloud.platform.routing.service..*.insert*(..)) || " +
            "execution(* com.barry.cloud.platform.routing.service..*.update*(..)) || " +
            "execution(* com.barry.cloud.platform.routing.service..*.delete*(..)) || " +
            "execution(* com.barry.cloud.platform.routing.service..*.save*(..)) || " +
            "execution(* com.barry.cloud.platform.routing.service..*.modify*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        log.info("dataSource切换到：write");
    }
}