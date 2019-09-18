package com.cgb1904.common.config;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cgb1904.service.realm.ShiroUserRealm;

@Configuration
public class SpringShiroConfig {

	@Bean("securityManager")
	public SecurityManager newSecurityManager(
			ShiroUserRealm realm,
			CacheManager cacheManager) {
		DefaultWebSecurityManager sManager=
		new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(cacheManager);
		sManager.setRememberMeManager(newRememberMeManager());
		sManager.setSessionManager(newSessionManager());
		return sManager;
	}
	/**创建一个Bean对象,通过此对象创建一个过滤器工厂,
	 * 通过此工厂创建ShiroFilter对象,最后通过过滤器对象
	 * 对请求数据进行过滤*/
	@Bean("shiroFilterFactory")
	@Autowired
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(
			SecurityManager securityManager) {
		ShiroFilterFactoryBean fBean=
	    new ShiroFilterFactoryBean();
		fBean.setSecurityManager(securityManager);
	    //设置进行认证的url
		fBean.setLoginUrl("/doLoginUI");
		Map<String,String> fm=new LinkedHashMap<>();
	    //设置允许匿名访问的资源
		fm.put("/article/**", "anon");
	    fm.put("/back_manage/**", "anon");
	    fm.put("/bower_components/**", "anon");
	    fm.put("/build/**", "anon");
	    fm.put("/dist/**", "anon");
	    fm.put("/user/**", "anon");
	    fm.put("/plugins/**", "anon");
	    fm.put("/reply/**", "anon");
	    //用户模块
	    fm.put("/*", "anon");
	    fm.put("/notice/*", "anon");
	    fm.put("/post/*", "anon");
	    fm.put("/login/*", "anon");
	    fm.put("/user/*", "anon");
	    //后台管理模块
	    fm.put("/back/*", "anon");
	    fm.put("/comment/*", "anon");
	    fm.put("/log/*", "anon");
	    fm.put("/manager/*", "anon");
	    fm.put("/posts/*", "anon");
	    fm.put("/users/*", "anon");
	    //登出
	    fm.put("/logout/doLogout", "logout");
	    //设置必须认证才可以访问的资源
	    fm.put("/**", "user");
		fBean.setFilterChainDefinitionMap(fm);
		return fBean;
	}
	//=========配置授权=======================
	//1.配置一个Shiro 中 Bean对象的生命周期管理器
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**为目标对象(方法上有@RequiresPermissions注解的)
	 * 创建代理对象,通过代理对象调用通知(Advice)实现
	 * 授权检测*/
//	@DependsOn("lifecycleBeanPostProcessor")
//	@Bean
//	public DefaultAdvisorAutoProxyCreator newProxyCreator() {
//		return new DefaultAdvisorAutoProxyCreator();
//	}
	/**
	 * 此对象负责定义切入点以及通知增强.
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor newAdvisor(
			SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor=
			new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	//=======================
	//配置shiro缓存对象
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
	 
	 /**
	  * 配置LogoutFilter
	  * @return
	  */
	 public ShiroLogoutFilter shiroLogoutFilter(){
	     ShiroLogoutFilter shiroLogoutFilter = new ShiroLogoutFilter();
	     //配置登出后重定向的地址，等出后配置跳转到登录接口
	     shiroLogoutFilter.setRedirectUrl("/doIndexUI");
	     return shiroLogoutFilter;
	 }
	
}








