package com.barry.cloud.platform.security.jwt.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.barry.cloud.platform.security.jwt.properties.DBProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Slf4j
@Configuration
public class DruidDBConfig {

    @Autowired
    private DBProperties dbProperties;

    @Value("${druid.datasource.login.username}")
    private String monitorUserName;

    @Value("${druid.datasource.login.password}")
    private String monitorPassword;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", monitorUserName);
        reg.addInitParameter("loginPassword", monitorPassword);
        reg.addInitParameter("logSlowSql", dbProperties.getLogSlowSql());
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

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
