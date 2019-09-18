package com.cgb1904.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;


/**
 * @author: wangsaichao
 * @date: 2018/11/27
 * @description: 自定义 LogoutFilter
 */
public class ShiroLogoutFilter extends LogoutFilter {

	/**
	 * 自定义登出,登出之后,清理当前用户redis部分缓存信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		//在这里执行退出系统前需要清空的数据
		Subject subject=getSubject(request,response);
		String redirectUrl=getRedirectUrl(request,response,subject);
		ServletContext context= request.getServletContext();
		try {
			subject.logout();
			context.removeAttribute("error");
		}catch (SessionException e){
			e.printStackTrace();
		}
		issueRedirect(request,response,redirectUrl);
		return false;
	}
}
