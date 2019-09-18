package com.cy.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cy.pj.sys.service.realm.ShiroUserRealm;
@Configuration
public class SpringShiroConfig {
	/**@Bean 描述的方法,其返回值会交给spring管理
	    * @Bean 一般应用在整合第三bean资源时*/
	@Bean("securityManager")
		 public SecurityManager newSecurityManager(ShiroUserRealm realm,CacheManager cacheManager) {
			 DefaultWebSecurityManager sManager=
			 new DefaultWebSecurityManager();
			 sManager.setRealm(realm);
			 sManager.setCacheManager(cacheManager);
			 sManager.setRememberMeManager(newRememberMeManager());
				sManager.setSessionManager(newSessionManager());
			 return sManager;
		 }
		 @Bean("shiroFilterFactory")
		 public ShiroFilterFactoryBean newShiroFilterFactoryBean(
				 @Autowired SecurityManager securityManager) {
			 ShiroFilterFactoryBean sfBean=
			 new ShiroFilterFactoryBean();
			 sfBean.setSecurityManager(securityManager);
			//假如没有认证请求先访问此认证的url
			 sfBean.setLoginUrl("/doLoginUI");
			 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
			 LinkedHashMap<String,String> map=
					 new LinkedHashMap<>();
			 //静态资源允许匿名访问:"anon"
			 map.put("/bower_components/**","anon");
			 map.put("/build/**","anon");
			 map.put("/dist/**","anon");
			 map.put("/plugins/**","anon");
			 map.put("/manager/doLogin","anon");
			 map.put("/doLogout","logout");
			 map.put("/doIndexUI","anon");
			 //除了匿名访问的资源,其它都要认证("authc")后访问
//		 map.put("/**","user");
		/* map.put("/**","authc"); */
		/* map.put("/*","anon"); */
			 sfBean.setFilterChainDefinitionMap(map);
			 return sfBean;
		 }
		 @Bean
			public AuthorizationAttributeSourceAdvisor newAdvisor(
					SecurityManager securityManager) {
				AuthorizationAttributeSourceAdvisor advisor=
					new AuthorizationAttributeSourceAdvisor();
				advisor.setSecurityManager(securityManager);
				return advisor;
			}
		 @Bean
			public CacheManager newCacheManager(){
			  return new MemoryConstrainedCacheManager();
		    }
			//配置cookie对象
			public CookieRememberMeManager newRememberMeManager() {
				 CookieRememberMeManager cManager=
				 new CookieRememberMeManager();
				 SimpleCookie c=new SimpleCookie("rememberMe");
				 c.setMaxAge(10*60);
				 cManager.setCookie(c);
				 return cManager;
			 }
			//默认 Session时长设置
			 public DefaultWebSessionManager newSessionManager() {
				 DefaultWebSessionManager sManager=
						 new DefaultWebSessionManager();
				 sManager.setGlobalSessionTimeout(60*60*1000);
				 return sManager;
			 }
}
