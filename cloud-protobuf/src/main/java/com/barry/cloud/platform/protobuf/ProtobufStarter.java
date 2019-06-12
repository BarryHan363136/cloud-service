package com.barry.cloud.platform.protobuf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * https://blog.csdn.net/fangxiaoji/article/details/78826165
 * https://www.cnblogs.com/kaituorensheng/p/9022591.html
 * https://blog.csdn.net/cowbin2012/article/details/85290876
 * https://www.cnblogs.com/sanshengshui/p/9741655.html
 *
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
public class ProtobufStarter {

    public static void main(String[] args) {
        SpringApplication.run(ProtobufStarter.class, args);
    }

}
