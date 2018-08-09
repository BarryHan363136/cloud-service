package com.barry.cloud.platform.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.barry.cloud.platform.datasource.properties.DBProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Slf4j
@Configuration
public class DruidDBConfig {

    @Autowired
    private DBProperties dbProperties;

    /**
     * 声明其为Bean实例
     * 如果同一个类型有多个实例，但需要注入一个的时候使用
     * 在同样的DataSource中，首先使用被标注的DataSource
     * */
    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
            datasource.setUrl(dbProperties.getUrl());
            datasource.setUsername(dbProperties.getUsername());
            datasource.setPassword(dbProperties.getUserpassword());
            datasource.setDriverClassName(dbProperties.getDriverClassName());
            // configuration
            datasource.setInitialSize(dbProperties.getInitialSize());
            datasource.setMinIdle(dbProperties.getMinIdle());
            datasource.setMaxActive(dbProperties.getMaxActive());
            datasource.setMaxWait(dbProperties.getMaxWait());
            datasource.setTimeBetweenEvictionRunsMillis(dbProperties.getTimeBetweenEvictionRunsMillis());
            datasource.setMinEvictableIdleTimeMillis(dbProperties.getMinEvictableIdleTimeMillis());
            datasource.setValidationQuery(dbProperties.getValidationQuery());
            datasource.setTestWhileIdle(dbProperties.isTestWhileIdle());
            datasource.setTestOnBorrow(dbProperties.isTestOnBorrow());
            datasource.setTestOnReturn(dbProperties.isTestOnReturn());
            datasource.setPoolPreparedStatements(dbProperties.isPoolPreparedStatements());
            datasource.setMaxPoolPreparedStatementPerConnectionSize(dbProperties.getMaxPoolPreparedStatementPerConnectionSize());
            datasource.setFilters(dbProperties.getFilters());
            datasource.setConnectionProperties(dbProperties.getConnectionProperties());
            return datasource;
        } catch (Exception e) {
            log.error("druid configuration initialization error {} ", e);
            return null;
        }
    }

}
