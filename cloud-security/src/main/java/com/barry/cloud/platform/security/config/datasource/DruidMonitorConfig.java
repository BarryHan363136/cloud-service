package com.barry.cloud.platform.security.config.datasource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/23 10:20
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "spring.datasource.access")
@SpringBootConfiguration
public class DruidMonitorConfig {

    private String allow;
    private String deny;
    private String loginUserName;
    private String loginPassword;
    private String resetEnable;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        log.info("init Druid Monitor Servlet ...");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addUrlMappings("/druid/*");

        /** IP白名单 */
        servletRegistrationBean.addInitParameter("allow", allow);
        /** IP黑名单(共同存在时，deny优先于allow) */
        servletRegistrationBean.addInitParameter("deny", deny);
        /** 控制台管理用户 */
        servletRegistrationBean.addInitParameter("loginUsername", loginUserName);
        servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
        /** 是否能够重置数据 禁用HTML页面上的“Reset All”功能 */
        servletRegistrationBean.addInitParameter("resetEnable", resetEnable);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}