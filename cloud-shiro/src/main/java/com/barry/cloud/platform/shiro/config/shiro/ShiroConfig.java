package com.barry.cloud.platform.shiro.config.shiro;

import com.barry.cloud.platform.shiro.entity.SysPermission;
import com.barry.cloud.platform.shiro.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Configuration
@AutoConfigureAfter(ShiroLifecycleBeanPostProcessorConfig.class)
public class ShiroConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.password}")
	private String password;

	@Resource
	private SysPermissionService sysPermissionService;

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		log.info("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		/** 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面 */
		shiroFilterFactoryBean.setLoginUrl("/turnLogin");
		/** 登录成功后要跳转的链接 */
		shiroFilterFactoryBean.setSuccessUrl("/index");
		/** 未授权界面; */
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");


		/** 拦截器 */
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		/** 配置不会被拦截的链接 顺序判断 */
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/templates/**", "anon");
		/** 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了 */
		filterChainDefinitionMap.put("/logout", "logout");
		/**
		 * 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了
		 * authc:所有url都必须认证通过才可以访问; anon:所有url都可以匿名访问
		 * */
		List<SysPermission> permissionList = sysPermissionService.findResults(null);
		if (permissionList!=null && !permissionList.isEmpty()){
			for(SysPermission sysPermission : permissionList){
				if (StringUtils.isNotEmpty(sysPermission.getUrl())) {
					String permission = "perms[" + sysPermission.getUrl()+ "]";
					filterChainDefinitionMap.put(sysPermission.getUrl(), permission);
				}
			}
		}
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 凭证匹配器
	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		/** 散列算法:这里使用MD5算法 */
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		/** 散列的次数，比如散列两次，相当于 md5(md5("")); */
		hashedCredentialsMatcher.setHashIterations(2);
		return hashedCredentialsMatcher;
	}


	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}


	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException","403");
		r.setExceptionMappings(mappings);  // None by default
		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("ex");     // Default is "exception"
		//r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}








	/**
	 * 配置shiro redisManager
	 * 使用的是shiro-redis开源插件
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		//redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(timeout);
		redisManager.setPassword(password);
		return redisManager;
	}

	/**
	 * cacheManager 缓存 redis实现
	 * 使用的是shiro-redis开源插件
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}


	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * shiro session的管理
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}




}