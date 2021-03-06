package com.barry.cloud.platform.datasource.monitor;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监控地址: http://ip:port/druid/
 *
 * */

@Configuration
public class DruidMonitor {

    @Value("${druid.datasource.login.username}")
    private String loginUsername;

    @Value("${druid.datasource.login.password}")
    private String loginPassword;

    /**
     * 注册一个StatViewServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServle2() {
        /** org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册. */
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addUrlMappings("/druid/*");

        /** 添加初始化参数：initParams */
        /** 白名单 */
        servletRegistrationBean.addInitParameter("allow", "");
        /** IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page. */
        servletRegistrationBean.addInitParameter("deny", "");
        /** 登录查看信息的账号密码. */
        servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
        /** 是否能够重置数据. */
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        /** 添加过滤规则. */
        filterRegistrationBean.addUrlPatterns("/*");
        /** 添加不需要忽略的格式信息. */
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }

}
