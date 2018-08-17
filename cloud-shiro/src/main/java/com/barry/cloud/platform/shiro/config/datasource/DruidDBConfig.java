package com.barry.cloud.platform.shiro.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.barry.cloud.platform.shiro.properties.DSProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DruidDBConfig {

    @Autowired
    private DSProperties dsProperties;

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
            datasource.setUrl(dsProperties.getUrl());
            datasource.setUsername(dsProperties.getUsername());
            datasource.setPassword(dsProperties.getUserpassword());
            datasource.setDriverClassName(dsProperties.getDriverClassName());
            // configuration
            datasource.setInitialSize(dsProperties.getInitialSize());
            datasource.setMinIdle(dsProperties.getMinIdle());
            datasource.setMaxActive(dsProperties.getMaxActive());
            datasource.setMaxWait(dsProperties.getMaxWait());
            datasource.setTimeBetweenEvictionRunsMillis(dsProperties.getTimeBetweenEvictionRunsMillis());
            datasource.setMinEvictableIdleTimeMillis(dsProperties.getMinEvictableIdleTimeMillis());
            datasource.setValidationQuery(dsProperties.getValidationQuery());
            datasource.setTestWhileIdle(dsProperties.isTestWhileIdle());
            datasource.setTestOnBorrow(dsProperties.isTestOnBorrow());
            datasource.setTestOnReturn(dsProperties.isTestOnReturn());
            datasource.setPoolPreparedStatements(dsProperties.isPoolPreparedStatements());
            datasource.setMaxPoolPreparedStatementPerConnectionSize(dsProperties.getMaxPoolPreparedStatementPerConnectionSize());
            datasource.setFilters(dsProperties.getFilters());
            datasource.setConnectionProperties(dsProperties.getConnectionProperties());
            return datasource;
        } catch (Exception e) {
            log.error("druid configuration initialization error {} ", e);
            return null;
        }
    }

}
