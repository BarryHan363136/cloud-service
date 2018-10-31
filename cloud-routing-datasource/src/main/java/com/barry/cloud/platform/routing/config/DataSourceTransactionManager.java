package com.barry.cloud.platform.routing.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Description:
 * @Author Tongshan.Han@partner.bmw.com
 * @Date 2018/10/30 17:53
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {

    @Resource(name = "writeDataSource")
    private DataSource dataSource;

    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，
     * 只要org.mybatis.spring.SqlSessionFactoryBean
     * 引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
     *
     * @return
     */
    @Bean(name = "transactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        log.info("-------------------- transactionManager init ---------------------");
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }

}