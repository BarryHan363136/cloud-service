package com.barry.cloud.platform.easyexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * TaskApplication [spring boot] 主方法
 *
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 *
 * https://mp.weixin.qq.com/s?__biz=MzI4ODQ3NjE2OA==&mid=2247485636&idx=1&sn=a5cfa9235f199e3bb59a9b247887e863&chksm=ec3c95a3db4b1cb5ed7777a25106131ebd6c55024da8cae4dbe9ee2e4aa31f17253b7decb495&mpshare=1&scene=1&srcid=0924LKg2dR9eAvj9oent1Qsy&sharer_sharetime=1569303562027&sharer_shareid=2239fc445ef79c30a20b8b36e0aa0479&pass_ticket=iS12o8J%2FJldhm8i430K7WI6M3enpnaaQSGkL6uQ%2BxXvUTF2hzDjvdaIe4RmQR8Xn#rd
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
public class EasyExcelStarter {

    public static void main(String[] args) {
        SpringApplication.run(EasyExcelStarter.class, args);
    }

}
