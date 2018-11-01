package com.barry.cloud.platform.routing.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 用于切换数据源的全局变量
 * @Author Tongshan.Han@partner.bmw.com
 * @Date 2018/10/30 17:45
 */
@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        log.info("数据库切换到读库...");
        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        log.info("数据库切换到写库...");
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}